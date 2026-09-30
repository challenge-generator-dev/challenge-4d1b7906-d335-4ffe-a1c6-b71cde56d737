package com.ecommerce.repository;

import com.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    List<Product> findByCategory(String category);

    Page<Product> findByCategory(String category, Pageable pageable);

    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    List<Product> findByStockLessThan(Integer stock);

    Page<Product> findByStockLessThan(Integer stock, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    List<Product> findProductsInStock();

    @Query("SELECT p FROM Product p WHERE p.stock > 0")
    Page<Product> findProductsInStock(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> searchByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    List<Product> findAvailableByCategory(@Param("category") String category);

    @Query("SELECT p FROM Product p WHERE p.category = :category AND p.stock > 0")
    Page<Product> findAvailableByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN p.orders o WHERE o.id = :orderId")
    List<Product> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT COUNT(p) FROM Product p WHERE p.category = :category")
    Long countByCategory(@Param("category") String category);

    @Query("SELECT DISTINCT p.category FROM Product p")
    List<String> findAllCategories();

    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    Page<Product> findAllOrderByPriceAsc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.price DESC")
    Page<Product> findAllOrderByPriceDesc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.stock ASC")
    Page<Product> findAllOrderByStockAsc(Pageable pageable);

    @Query("SELECT p FROM Product p ORDER BY p.stock DESC")
    Page<Product> findAllOrderByStockDesc(Pageable pageable);

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Product p WHERE p.name = :name AND p.id <> :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);
}