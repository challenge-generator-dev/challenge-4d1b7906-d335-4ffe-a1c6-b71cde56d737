package com.ecommerce.service;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Servicio de negocio para la gestión de pedidos.
 * Maneja la lógica de creación, actualización y consulta de pedidos,
 * incluyendo validación de productos, cálculo de totales y gestión de inventario.
 */
@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    /**
     * Crea un nuevo pedido en el sistema.
     * Valida que el cliente exista, que los productos sean válidos,
     * que haya suficiente stock y calcula el total del pedido.
     */
    public OrderDto createOrder(OrderDto orderDto) {
        validateOrderData(orderDto);

        List<Product> products = validateAndFetchProducts(orderDto.getProductIds());
        BigDecimal totalAmount = calculateTotalAmount(products);
        int totalQuantity = products.size();

        Order order = new Order();
        order.setCustomerId(orderDto.getCustomerId());
        order.setProducts(new HashSet<>(products));
        order.setTotalQuantity(totalQuantity);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        updateProductStock(products);

        Order savedOrder = orderRepository.save(order);
        return mapToDto(savedOrder);
    }

    /**
     * Actualiza el estado de un pedido existente.
     * Los estados válidos incluyen: PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED.
     */
    public OrderDto updateOrderStatus(Long id, String newStatus) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));

        validateStatus(newStatus);

        String currentStatus = order.getStatus();
        if ("CANCELLED".equals(currentStatus)) {
            throw new InvalidInputException(
                "No se puede modificar un pedido cancelado",
                "status", newStatus);
        }

        if ("DELIVERED".equals(currentStatus)) {
            throw new InvalidInputException(
                "No se puede modificar un pedido entregado",
                "status", newStatus);
        }

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return mapToDto(updatedOrder);
    }

    /**
     * Cancela un pedido si aún no ha sido enviado.
     * Restaura el stock de los productos asociados.
     */
    public OrderDto cancelOrder(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));

        if ("SHIPPED".equals(order.getStatus()) || "DELIVERED".equals(order.getStatus())) {
            throw new InvalidInputException(
                "No se puede cancelar un pedido que ya ha sido enviado o entregado",
                "status", order.getStatus());
        }

        if ("CANCELLED".equals(order.getStatus())) {
            throw new InvalidInputException(
                "El pedido ya está cancelado",
                "status", order.getStatus());
        }

        restoreProductStock(order.getProducts());
        order.setStatus("CANCELLED");

        Order cancelledOrder = orderRepository.save(order);
        return mapToDto(cancelledOrder);
    }

    /**
     * Busca un pedido por su ID.
     * Lanza ResourceNotFoundException si no existe.
     */
    @Transactional(readOnly = true)
    public OrderDto findOrderById(Long id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Pedido no encontrado con ID: " + id, "id", id));
        return mapToDto(order);
    }

    /**
     * Obtiene todos los pedidos con soporte de paginación.
     * Ordena por fecha de pedido de forma descendente por defecto.
     */
    @Transactional(readOnly = true)
    public Page<OrderDto> findAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
            .map(this::mapToDto);
    }

    /**
     * Busca pedidos de un cliente específico.
     * Útil para el historial de compras de un usuario.
     */
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByCustomerId(Long customerId) {
        if (customerId == null) {
            throw new InvalidInputException(
                "El ID del cliente es obligatorio",
                "customerId", customerId);
        }

        return orderRepository.findAll().stream()
            .filter(o -> o.getCustomerId() != null 
                && o.getCustomerId().equals(customerId))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Busca pedidos por estado.
     * Útil para filtros en paneles de administración.
     */
    @Transactional(readOnly = true)
    public List<OrderDto> findOrdersByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException(
                "El estado del pedido es obligatorio",
                "status");
        }

        return orderRepository.findAll().stream()
            .filter(o -> o.getStatus() != null 
                && o.getStatus().equalsIgnoreCase(status))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Valida los datos de un pedido antes de procesarlo.
     */
    private void validateOrderData(OrderDto orderDto) {
        if (orderDto.getCustomerId() == null) {
            throw new InvalidInputException(
                "El ID del cliente es obligatorio",
                "customerId");
        }

        if (orderDto.getProductIds() == null || orderDto.getProductIds().isEmpty()) {
            throw new InvalidInputException(
                "El pedido debe contener al menos un producto",
                "productIds");
        }

        if (orderDto.getProductIds().size() > 100) {
            throw new InvalidInputException(
                "Un pedido no puede contener más de 100 productos",
                "productIds");
        }
    }

    /**
     * Valida que los productos existan y estén disponibles.
     * Retorna la lista de productos validados.
     */
    private List<Product> validateAndFetchProducts(List<Long> productIds) {
        Set<Long> uniqueIds = new HashSet<>(productIds);
        List<Product> products = new ArrayList<>();

        for (Long productId : uniqueIds) {
            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Producto no encontrado con ID: " + productId, "productId", productId));

            if (product.getStock() == null || product.getStock() <= 0) {
                throw new InvalidInputException(
                    "Producto sin stock disponible: " + product.getName(),
                    "productId", productId);
            }

            products.add(product);
        }

        return products;
    }

    /**
     * Calcula el monto total del pedido sumando los precios de los productos.
     */
    private BigDecimal calculateTotalAmount(List<Product> products) {
        return products.stream()
            .filter(p -> p.getPrice() != null)
            .map(Product::getPrice)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Valida que el estado proporcionado sea válido.
     */
    private void validateStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new InvalidInputException(
                "El estado del pedido es obligatorio",
                "status");
        }

        List<String> validStatuses = List.of(
            "PENDING", "CONFIRMED", "SHIPPED", "DELIVERED", "CANCELLED"
        );

        if (!validStatuses.contains(status.toUpperCase())) {
            throw new InvalidInputException(
                "Estado de pedido inválido. Estados válidos: " + validStatuses,
                "status", status);
        }
    }

    /**
     * Actualiza el stock de los productos restando las cantidades vendidas.
     */
    private void updateProductStock(List<Product> products) {
        for (Product product : products) {
            int newStock = product.getStock() - 1;
            product.setStock(Math.max(0, newStock));
            productRepository.save(product);
        }
    }

    /**
     * Restaura el stock de los productos al cancelar un pedido.
     */
    private void restoreProductStock(Set<Product> products) {
        for (Product product : products) {
            int restoredStock = product.getStock() + 1;
            product.setStock(restoredStock);
            productRepository.save(product);
        }
    }

    /**
     * Transforma una entidad Order a un OrderDto.
     * Incluye los IDs de los productos asociados.
     */
    private OrderDto mapToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setTotalQuantity(order.getTotalQuantity());
        dto.setTotalAmount(order.getTotalAmount());
        dto.setOrderDate(order.getOrderDate());
        dto.setStatus(order.getStatus());

        if (order.getProducts() != null) {
            List<Long> productIds = order.getProducts().stream()
                .map(Product::getId)
                .collect(Collectors.toList());
            dto.setProductIds(productIds);
        }

        return dto;
    }
}