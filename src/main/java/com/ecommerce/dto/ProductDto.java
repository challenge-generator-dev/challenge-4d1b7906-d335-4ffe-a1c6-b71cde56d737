package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Producto")
public class ProductDto {
    @Schema(description = "Identificador único del producto", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Schema(description = "Nombre del producto", example = "Laptop Gamer")
    private String name;

    @NotBlank(message = "La descripción del producto es obligatoria")
    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Schema(description = "Descripción detallada del producto", example = "Laptop con procesador i7 y 16GB de RAM")
    private String description;

    @NotNull(message = "El precio del producto es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor que 0")
    @Schema(description = "Precio del producto", example = "999.99")
    private BigDecimal price;

    @NotNull(message = "La cantidad en stock es obligatoria")
    @Min(value = 0, message = "La cantidad en stock no puede ser negativa")
    @Schema(description = "Cantidad disponible en stock", example = "50")
    private Integer stock;

    @NotBlank(message = "La categoría del producto es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    @Schema(description = "Categoría del producto", example = "Electrónica")
    private String category;

    @Schema(description = "Lista de IDs de pedidos en los que aparece el producto")
    private List<Long> orderIds;
}