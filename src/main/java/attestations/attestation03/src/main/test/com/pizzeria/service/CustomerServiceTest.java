package com.pizzeria.service;

import com.pizzeria.dto.CustomerDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Customer;
import com.pizzeria.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceTest {

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() {
        // Входной DTO
        CustomerDTO inputDTO = new CustomerDTO();
        inputDTO.setName("Игорь Пупков");
        inputDTO.setPhone("1234567890");
        inputDTO.setEmail("Igor@example.com");

        // Ожидаемая сущность
        Customer customer = new Customer();
        customer.setName("Игорь Пупков");
        customer.setPhone("1234567890");
        customer.setEmail("Igor@example.com");

        // Сохранённая сущность
        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setName("Игорь Пупков");
        savedCustomer.setPhone("1234567890");
        savedCustomer.setEmail("Igor@example.com");

        // Ожидаемый выходной DTO
        CustomerDTO expectedDTO = new CustomerDTO();
        expectedDTO.setId(1L);
        expectedDTO.setName("Игорь Пупков");
        expectedDTO.setPhone("1234567890");
        expectedDTO.setEmail("Igor@example.com");

        when(customerRepository.existsByEmail("Igor@example.com")).thenReturn(false);
        when(modelMapper.map(inputDTO, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(modelMapper.map(savedCustomer, CustomerDTO.class)).thenReturn(expectedDTO);

        CustomerDTO result = customerService.createCustomer(inputDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Игорь Пупков", result.getName());
        assertEquals("Igor@example.com", result.getEmail());
        verify(customerRepository, times(1)).save(customer);
    }

    @Test
    public void testCreateCustomer_WithExistingEmail_ShouldThrowException() {
        CustomerDTO inputDTO = new CustomerDTO();
        inputDTO.setEmail("existing@example.com");

        when(customerRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(inputDTO);
        });
    }

    @Test
    public void testGetCustomerById() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setName("Игорь Пупков");

        CustomerDTO expectedDTO = new CustomerDTO();
        expectedDTO.setId(customerId);
        expectedDTO.setName("Игорь Пупков");

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(expectedDTO);

        CustomerDTO result = customerService.getCustomerById(customerId);

        assertNotNull(result);
        assertEquals(customerId, result.getId());
        assertEquals("Игорь Пупков", result.getName());
        verify(customerRepository, times(1)).findById(customerId);
    }

    @Test
    public void testGetCustomerById_WhenNotFound_ShouldThrowException() {
        Long customerId = 99L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
    }
}