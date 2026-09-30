package com.ecommerce.controller;

import com.ecommerce.dto.CustomerDto;
import com.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Gestión de Clientes", description = "Operaciones CRUD para la gestión de clientes del e-commerce")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema con los datos proporcionados")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto created = customerService.createCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente por ID", description = "Retrieves a specific customer by their unique identifier")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<CustomerDto> getCustomerById(
            @Parameter(description = "ID único del cliente a buscar", required = true) @PathVariable Long id) {
        CustomerDto customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes", description = "Retrieves a paginated list of all customers in the system")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(
            @Parameter(description = "Configuración de paginación y ordenamiento") @PageableDefault(size = 10, sort = "name") Pageable pageable) {
        Page<CustomerDto> customers = customerService.getAllCustomers(pageable);
        return ResponseEntity.ok(customers);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar cliente", description = "Updates an existing customer's information with the provided data")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> updateCustomer(
            @Parameter(description = "ID único del cliente a actualizar", required = true) @PathVariable Long id,
            @Valid @RequestBody CustomerDto customerDto) {
        CustomerDto updated = customerService.updateCustomer(id, customerDto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar cliente", description = "Removes a customer from the system using their unique identifier")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(
            @Parameter(description = "ID único del cliente a eliminar", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Buscar clientes por email", description = "Retrieves customers matching the provided email pattern")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<CustomerDto>> searchCustomersByEmail(
            @Parameter(description = "Email o patrón de búsqueda", required = true) @RequestParam String email) {
        List<CustomerDto> customers = customerService.searchCustomersByEmail(email);
        return ResponseEntity.ok(customers);
    }
}