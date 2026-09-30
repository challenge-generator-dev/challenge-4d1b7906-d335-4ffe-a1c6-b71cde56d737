package com.ecommerce.controller;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Gestión de Pedidos", description = "Endpoints para operaciones CRUD de pedidos")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @Operation(summary = "Listar pedidos", description = "Obtiene una lista paginada de todos los pedidos")
    public ResponseEntity<Page<OrderDto>> getAllOrders(
            @Parameter(description = "Número de página (0-indexed)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Tamaño de página") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Campo de ordenamiento") @RequestParam(defaultValue = "id") String sortBy,
            @Parameter(description = "Dirección de ordenamiento") @RequestParam(defaultValue = "desc") String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<OrderDto> orders = orderService.getAllOrders(pageable);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener pedido por ID", description = "Retorna un pedido específico por su identificador")
    public ResponseEntity<OrderDto> getOrderById(
            @Parameter(description = "ID del pedido a buscar") @PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @PostMapping
    @Operation(summary = "Crear pedido", description = "Crea un nuevo pedido en el sistema")
    public ResponseEntity<OrderDto> createOrder(
            @Parameter(description = "Datos del pedido a crear") @Valid @RequestBody OrderDto orderDto) {
        OrderDto createdOrder = orderService.createOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar pedido", description = "Actualiza los datos de un pedido existente")
    public ResponseEntity<OrderDto> updateOrder(
            @Parameter(description = "ID del pedido a actualizar") @PathVariable Long id,
            @Parameter(description = "Nuevos datos del pedido") @Valid @RequestBody OrderDto orderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, orderDto);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pedido", description = "Elimina un pedido del sistema por su ID")
    public ResponseEntity<Void> deleteOrder(
            @Parameter(description = "ID del pedido a eliminar") @PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Buscar por cliente", description = "Obtiene todos los pedidos de un cliente específico")
    public ResponseEntity<List<OrderDto>> getOrdersByCustomerId(
            @Parameter(description = "ID del cliente") @PathVariable Long customerId) {
        List<OrderDto> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Buscar por estado", description = "Obtiene pedidos filtrados por estado")
    public ResponseEntity<List<OrderDto>> getOrdersByStatus(
            @Parameter(description = "Estado del pedido") @PathVariable String status) {
        List<OrderDto> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Actualizar estado", description = "Actualiza el estado de un pedido")
    public ResponseEntity<OrderDto> updateOrderStatus(
            @Parameter(description = "ID del pedido") @PathVariable Long id,
            @Parameter(description = "Nuevo estado del pedido") @RequestParam String status) {
        OrderDto updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
}