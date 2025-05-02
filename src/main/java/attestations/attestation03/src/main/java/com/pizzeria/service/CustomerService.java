package com.pizzeria.service;

import com.pizzeria.dto.CustomerDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Customer;
import com.pizzeria.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public List<CustomerDTO> getAllActiveCustomers() {
        return customerRepository.findAllByActiveTrue()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        if (customerRepository.existsByEmail(customerDTO.getEmail())) {
            throw new IllegalArgumentException("Email already in use");
        }

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setActive(true);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        if (customerDTO.getEmail() != null && !existingCustomer.getEmail().equals(customerDTO.getEmail())) {
            if (customerRepository.existsByEmail(customerDTO.getEmail())) {
                throw new IllegalArgumentException("Email already in use");
            }
            existingCustomer.setEmail(customerDTO.getEmail());
        }

        modelMapper.map(customerDTO, existingCustomer);
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customer.setActive(false);
        customerRepository.save(customer);
    }
}