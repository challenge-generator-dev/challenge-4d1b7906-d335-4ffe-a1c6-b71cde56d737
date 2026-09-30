package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Pedido")
public class OrderDto {
    @Schema(description = "Identificador único del pedido", example = "1")
    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    @Schema(description = "ID del cliente que realizó el pedido", example = "1")
    private Long customerId;

    @NotEmpty(message = "El pedido debe contener al menos un producto")
    @Schema(description = "Lista de IDs de productos incluidos en el pedido")
    private List<Long> productIds;

    @NotNull(message = "La cantidad total de productos es obligatoria")
    @Min(value = 1, message = "La cantidad total debe ser al menos 1")
    @Schema(description = "Cantidad total de productos en el pedido", example = "2")
    private Integer totalQuantity;

    @NotNull(message = "El monto total del pedido es obligatorio")
    @DecimalMin(value = "0.01", message = "El monto total debe ser mayor que 0")
    @Schema(description = "Monto total del pedido", example = "1999.98")
    private BigDecimal totalAmount;

    @NotNull(message = "La fecha del pedido es obligatoria")
    @Schema(description = "Fecha y hora en que se realizó el pedido", example = "2024-05-20T10:30:00")
    private LocalDateTime orderDate;

    @NotBlank(message = "El estado del pedido es obligatorio")
    @Size(max = 20, message = "El estado no puede exceder 20 caracteres")
    @Schema(description = "Estado actual del pedido", example = "PENDING")
    private String status;
}