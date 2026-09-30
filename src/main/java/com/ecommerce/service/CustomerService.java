package com.ecommerce.service;

import com.ecommerce.dto.CustomerDto;
import com.ecommerce.exception.InvalidInputException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.model.Customer;
import com.ecommerce.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<CustomerDto> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(this::toDto);
    }

    public List<CustomerDto> findAllWithoutPagination() {
        return customerRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));
        return toDto(customer);
    }

    public CustomerDto findByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con email: " + email));
    }

    public CustomerDto create(CustomerDto customerDto) {
        validateCustomerDto(customerDto);
        
        if (customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
            throw new InvalidInputException("El email ya está registrado: " + customerDto.getEmail());
        }

        Customer customer = toEntity(customerDto);
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        
        if (customer.getRole() == null || customer.getRole().isEmpty()) {
            customer.setRole("USER");
        }

        Customer savedCustomer = customerRepository.save(customer);
        return toDto(savedCustomer);
    }

    public CustomerDto update(Long id, CustomerDto customerDto) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        if (customerDto.getName() != null && !customerDto.getName().isEmpty()) {
            existingCustomer.setName(customerDto.getName());
        }
        if (customerDto.getEmail() != null && !customerDto.getEmail().isEmpty()) {
            if (!existingCustomer.getEmail().equals(customerDto.getEmail()) &&
                customerRepository.findByEmail(customerDto.getEmail()).isPresent()) {
                throw new InvalidInputException("El email ya está registrado: " + customerDto.getEmail());
            }
            existingCustomer.setEmail(customerDto.getEmail());
        }
        if (customerDto.getPassword() != null && !customerDto.getPassword().isEmpty()) {
            existingCustomer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
        }
        if (customerDto.getAddress() != null) {
            existingCustomer.setAddress(customerDto.getAddress());
        }
        if (customerDto.getPhone() != null) {
            existingCustomer.setPhone(customerDto.getPhone());
        }
        if (customerDto.getRole() != null && !customerDto.getRole().isEmpty()) {
            existingCustomer.setRole(customerDto.getRole());
        }

        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return toDto(updatedCustomer);
    }

    public void delete(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
        customerRepository.deleteById(id);
    }

    public CustomerDto authenticate(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidInputException("Credenciales inválidas"));
        
        if (!passwordEncoder.matches(password, customer.getPassword())) {
            throw new InvalidInputException("Credenciales inválidas");
        }
        
        return toDto(customer);
    }

    public boolean existsByEmail(String email) {
        return customerRepository.findByEmail(email).isPresent();
    }

    public long count() {
        return customerRepository.count();
    }

    private void validateCustomerDto(CustomerDto customerDto) {
        if (customerDto.getEmail() == null || customerDto.getEmail().isEmpty()) {
            throw new InvalidInputException("El email es obligatorio");
        }
        if (!customerDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("El formato del email es inválido");
        }
        if (customerDto.getPassword() == null || customerDto.getPassword().isEmpty()) {
            throw new InvalidInputException("La contraseña es obligatoria");
        }
        if (customerDto.getPassword().length() < 6) {
            throw new InvalidInputException("La contraseña debe tener al menos 6 caracteres");
        }
        if (customerDto.getName() == null || customerDto.getName().isEmpty()) {
            throw new InvalidInputException("El nombre es obligatorio");
        }
    }

    private CustomerDto toDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        dto.setPassword(customer.getPassword());
        dto.setAddress(customer.getAddress());
        dto.setPhone(customer.getPhone());
        dto.setRole(customer.getRole());
        if (customer.getOrders() != null) {
            dto.setOrderIds(customer.getOrders().stream()
                    .map(order -> order.getId())
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPassword(dto.getPassword());
        customer.setAddress(dto.getAddress());
        customer.setPhone(dto.getPhone());
        customer.setRole(dto.getRole() != null ? dto.getRole() : "USER");
        return customer;
    }
}