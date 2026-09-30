package com.ecommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
@Schema(description = "DTO para la entidad Cliente")
public class CustomerDto {
    @Schema(description = "Identificador único del cliente", example = "1")
    private Long id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    @Schema(description = "Nombre completo del cliente", example = "Juan Pérez")
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El correo electrónico debe ser válido")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    @Schema(description = "Correo electrónico del cliente", example = "juan.perez@example.com")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Schema(description = "Contraseña del cliente", example = "securePassword123")
    private String password;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    @Schema(description = "Dirección de envío del cliente", example = "Calle Principal 123, Ciudad")
    private String address;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20, message = "El teléfono no puede exceder 20 caracteres")
    @Schema(description = "Número de teléfono del cliente", example = "+1234567890")
    private String phone;

    @Schema(description = "Lista de IDs de pedidos realizados por el cliente")
    private List<Long> orderIds;

    @NotBlank(message = "El rol del cliente es obligatorio")
    @Size(max = 20, message = "El rol no puede exceder 20 caracteres")
    @Schema(description = "Rol del cliente en el sistema", example = "USER")
    private String role;
}