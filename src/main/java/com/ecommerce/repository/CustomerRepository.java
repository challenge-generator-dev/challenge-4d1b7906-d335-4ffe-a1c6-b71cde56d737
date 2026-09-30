package com.ecommerce.repository;

import com.ecommerce.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    List<Customer> findByRole(String role);

    Page<Customer> findByRole(String role, Pageable pageable);

    List<Customer> findByNameContaining(String name);

    Page<Customer> findByNameContaining(String name, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Customer> searchByName(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Customer> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.address LIKE %:city%")
    List<Customer> findByCity(@Param("city") String city);

    @Query("SELECT c FROM Customer c WHERE c.address LIKE %:city%")
    Page<Customer> findByCity(@Param("city") String city, Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.phone LIKE %:phone%")
    List<Customer> findByPhoneContaining(@Param("phone") String phone);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.id = :orderId")
    Optional<Customer> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.status = :status")
    List<Customer> findCustomersWithOrderStatus(@Param("status") String status);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.status = :status")
    Page<Customer> findCustomersWithOrderStatus(@Param("status") String status, Pageable pageable);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.role = :role")
    Long countByRole(@Param("role") String role);

    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Customer c WHERE c.email = :email AND c.id <> :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);

    @Query("SELECT c FROM Customer c WHERE c.role = 'USER' ORDER BY c.name ASC")
    Page<Customer> findAllUsersOrderByName(Pageable pageable);

    @Query("SELECT c FROM Customer c WHERE c.role = 'ADMIN'")
    List<Customer> findAllAdmins();

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.orderDate >= :since")
    List<Customer> findActiveCustomersSince(@Param("since") java.time.LocalDateTime since);

    @Query("SELECT c FROM Customer c JOIN c.orders o GROUP BY c.id HAVING COUNT(o) >= :minOrders")
    List<Customer> findCustomersWithMinOrders(@Param("minOrders") Long minOrders);

    @Query("SELECT c.role, COUNT(c) FROM Customer c GROUP BY c.role")
    List<Object[]> countCustomersByRole();

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain%")
    List<Customer> findByEmailDomain(@Param("domain") String domain);

    @Query("SELECT c FROM Customer c WHERE c.email LIKE %:domain%")
    Page<Customer> findByEmailDomain(@Param("domain") String domain, Pageable pageable);
}