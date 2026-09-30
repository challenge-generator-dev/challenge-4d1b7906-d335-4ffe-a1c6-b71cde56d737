package com.ecommerce.repository;

import com.ecommerce.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(Long customerId);

    Page<Order> findByCustomerId(Long customerId, Pageable pageable);

    List<Order> findByStatus(String status);

    Page<Order> findByStatus(String status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.status = :status")
    List<Order> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.status = :status")
    Page<Order> findByCustomerIdAndStatus(@Param("customerId") Long customerId, @Param("status") String status, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    List<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT o FROM Order o WHERE o.orderDate BETWEEN :startDate AND :endDate")
    Page<Order> findByOrderDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, Pageable pageable);

    @Query("SELECT o FROM Order o JOIN o.products p WHERE p.id = :productId")
    List<Order> findByProductId(@Param("productId") Long productId);

    @Query("SELECT o FROM Order o JOIN o.products p WHERE p.id = :productId")
    Page<Order> findByProductId(@Param("productId") Long productId, Pageable pageable);

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.orderDate >= :since")
    List<Order> findRecentOrdersByCustomer(@Param("customerId") Long customerId, @Param("since") LocalDateTime since);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = :status")
    Long countByStatus(@Param("status") String status);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.customer.id = :customerId")
    Long countByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate DESC")
    Page<Order> findAllOrderByOrderDateDesc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.orderDate ASC")
    Page<Order> findAllOrderByOrderDateAsc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.totalAmount DESC")
    Page<Order> findAllOrderByTotalAmountDesc(Pageable pageable);

    @Query("SELECT o FROM Order o ORDER BY o.totalAmount ASC")
    Page<Order> findAllOrderByTotalAmountAsc(Pageable pageable);

    @Query("SELECT o.status, COUNT(o) FROM Order o GROUP BY o.status")
    List<Object[]> countOrdersByStatus();

    @Query("SELECT o.customer.id, SUM(o.totalAmount) FROM Order o WHERE o.status = 'COMPLETED' GROUP BY o.customer.id")
    List<Object[]> findTotalSpentByCustomer();

    @Query("SELECT o FROM Order o WHERE o.status = 'COMPLETED' AND o.orderDate >= :startDate")
    List<Order> findCompletedOrdersSince(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT MAX(o.totalAmount) FROM Order o WHERE o.customer.id = :customerId")
    Optional<Double> findMaxOrderAmountByCustomer(@Param("customerId") Long customerId);
}