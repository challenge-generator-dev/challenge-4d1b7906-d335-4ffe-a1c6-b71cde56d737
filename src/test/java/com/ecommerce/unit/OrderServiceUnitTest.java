package com.ecommerce.unit;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Order;
import com.ecommerce.model.Product;
import com.ecommerce.repository.CustomerRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Unit Tests")
class OrderServiceUnitTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private OrderDto orderDto;
    private Customer customer;
    private Product product;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        product = new Product();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1299.99"));
        product.setStock(10);

        order = new Order();
        order.setId(1L);
        order.setCustomer(customer);
        order.setProducts(new HashSet<>(List.of(product)));
        order.setTotalQuantity(2);
        order.setTotalAmount(new BigDecimal("2599.98"));
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        orderDto = new OrderDto();
        orderDto.setId(1L);
        orderDto.setCustomerId(1L);
        orderDto.setProductIds(List.of(1L));
        orderDto.setTotalQuantity(2);
        orderDto.setTotalAmount(new BigDecimal("2599.98"));
        orderDto.setOrderDate(LocalDateTime.now());
        orderDto.setStatus("PENDING");
    }

    @Test
    @DisplayName("Should create an order successfully")
    void testCreateOrder() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto result = orderService.createOrder(orderDto);

        assertNotNull(result);
        assertEquals(1L, result.getCustomerId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should return all orders")
    void testFindAllOrders() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findAll()).thenReturn(orders);

        List<OrderDto> result = orderService.findAllOrders();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Should return order by id")
    void testFindOrderById() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        OrderDto result = orderService.findOrderById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when order not found")
    void testFindOrderByIdNotFound() {
        when(orderRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> orderService.findOrderById(999L));
    }

    @Test
    @DisplayName("Should update order status successfully")
    void testUpdateOrderStatus() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        OrderDto result = orderService.updateOrderStatus(1L, "COMPLETED");

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    @DisplayName("Should delete order successfully")
    void testDeleteOrder() {
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
        doNothing().when(orderRepository).delete(order);

        orderService.deleteOrder(1L);

        verify(orderRepository, times(1)).findById(1L);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    @DisplayName("Should return orders by customer id")
    void testFindOrdersByCustomerId() {
        List<Order> orders = Arrays.asList(order);
        when(orderRepository.findByCustomerId(1L)).thenReturn(orders);

        List<OrderDto> result = orderService.findOrdersByCustomerId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByCustomerId(1L);
    }
}