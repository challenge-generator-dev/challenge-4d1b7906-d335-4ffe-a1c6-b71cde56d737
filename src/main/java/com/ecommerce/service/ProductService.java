package com.ecommerce.service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de negocio para la gestión de productos.
 * Encapsula toda la lógica de negocio relacionada con productos,
 * incluyendo validación, transformación entre entidades y DTOs,
 * y coordinación con el repositorio para operaciones de persistencia.
 */
@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Crea un nuevo producto en el sistema.
     * Valida que el nombre no esté vacío, que el precio sea positivo
     * y que el stock no sea negativo antes de persistir.
     */
    public ProductDto createProduct(ProductDto productDto) {
        validateProductData(productDto);
        
        Product product = mapToEntity(productDto);
        Product savedProduct = productRepository.save(product);
        
        return mapToDto(savedProduct);
    }

    /**
     * Actualiza un producto existente identificado por su ID.
     * Si el producto no existe, lanza ResourceNotFoundException.
     * Valida los datos antes de actualizar.
     */
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product existingProduct = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));

        validateProductData(productDto);

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setCategory(productDto.getCategory());

        Product updatedProduct = productRepository.save(existingProduct);
        return mapToDto(updatedProduct);
    }

    /**
     * Elimina un producto por su ID.
     * Lanza exception si el producto no existe.
     */
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                "Producto no encontrado para eliminar con ID: " + id, "id", id);
        }
        productRepository.deleteById(id);
    }

    /**
     * Busca un producto por su ID.
     * Lanza ResourceNotFoundException si no se encuentra.
     */
    @Transactional(readOnly = true)
    public ProductDto findProductById(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));
        return mapToDto(product);
    }

    /**
     * Obtiene todos los productos con soporte de paginación.
     * El parámetro Pageable permite controlar página, tamaño y ordenamiento.
     */
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable)
            .map(this::mapToDto);
    }

    /**
     * Busca productos por categoría.
     * Retorna una lista vacía si no hay productos en esa categoría.
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findProductsByCategory(String category) {
        return productRepository.findAll().stream()
            .filter(p -> p.getCategory() != null && p.getCategory().equalsIgnoreCase(category))
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Busca productos cuyo precio está dentro de un rango.
     * Útil para funcionalidades de filtrado por precio.
     */
    @Transactional(readOnly = true)
    public List<ProductDto> findProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        if (minPrice == null || maxPrice == null) {
            throw new InvalidInputException(
                "Los precios mínimo y máximo son obligatorios para el filtrado",
                "priceRange");
        }
        if (minPrice.compareTo(maxPrice) > 0) {
            throw new InvalidInputException(
                "El precio mínimo no puede ser mayor que el máximo",
                "priceRange");
        }

        return productRepository.findAll().stream()
            .filter(p -> p.getPrice() != null)
            .filter(p -> p.getPrice().compareTo(minPrice) >= 0 
                && p.getPrice().compareTo(maxPrice) <= 0)
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    /**
     * Actualiza el stock de un producto.
     * Se utiliza cuando se procesa un pedido o se recibe inventario.
     */
    public ProductDto updateStock(Long id, Integer newStock) {
        if (newStock == null || newStock < 0) {
            throw new InvalidInputException(
                "El stock debe ser un número no negativo",
                "stock", newStock);
        }

        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Producto no encontrado con ID: " + id, "id", id));

        product.setStock(newStock);
        Product updatedProduct = productRepository.save(product);
        return mapToDto(updatedProduct);
    }

    /**
     * Valida los datos de un producto antes de persistir o actualizar.
     * Lanza InvalidInputException si los datos no son válidos.
     */
    private void validateProductData(ProductDto productDto) {
        if (productDto.getName() == null || productDto.getName().trim().isEmpty()) {
            throw new InvalidInputException(
                "El nombre del producto es obligatorio",
                "name", productDto.getName());
        }

        if (productDto.getName().length() > 200) {
            throw new InvalidInputException(
                "El nombre del producto no puede exceder 200 caracteres",
                "name", productDto.getName());
        }

        if (productDto.getDescription() != null && productDto.getDescription().length() > 1000) {
            throw new InvalidInputException(
                "La descripción no puede exceder 1000 caracteres",
                "description");
        }

        if (productDto.getPrice() == null) {
            throw new InvalidInputException(
                "El precio del producto es obligatorio",
                "price");
        }

        if (productDto.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException(
                "El precio debe ser mayor que cero",
                "price", productDto.getPrice());
        }

        if (productDto.getPrice().compareTo(new BigDecimal("999999.99")) > 0) {
            throw new InvalidInputException(
                "El precio no puede exceder 999999.99",
                "price", productDto.getPrice());
        }

        if (productDto.getStock() == null) {
            throw new InvalidInputException(
                "El stock del producto es obligatorio",
                "stock");
        }

        if (productDto.getStock() < 0) {
            throw new InvalidInputException(
                "El stock no puede ser negativo",
                "stock", productDto.getStock());
        }

        if (productDto.getCategory() != null && productDto.getCategory().length() > 100) {
            throw new InvalidInputException(
                "La categoría no puede exceder 100 caracteres",
                "category");
        }
    }

    /**
     * Transforma una entidad Product a un ProductDto.
     * Copia los campos relevantes omitting las relaciones para evitar
     * problemas de serialización circular.
     */
    private ProductDto mapToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStock(product.getStock());
        dto.setCategory(product.getCategory());
        return dto;
    }

    /**
     * Transforma un ProductDto a una entidad Product.
     * Se usa para crear nuevas entidades o actualizar existentes.
     */
    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        if (productDto.getId() != null) {
            product.setId(productDto.getId());
        }
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCategory(productDto.getCategory());
        return product;
    }
}