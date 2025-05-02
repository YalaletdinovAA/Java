package com.pizzeria.service;

import com.pizzeria.dto.CustomerDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Customer;
import com.pizzeria.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CustomerService customerService;

    //  проверяет корректное создание нового клиента
    @Test
    public void testCreateCustomer() {

        // Входной DTO (что передаём в метод)
        CustomerDTO inputDTO = new CustomerDTO();
        inputDTO.setName("Игорь Пупков");
        inputDTO.setPhone("1234567890");
        inputDTO.setEmail("Igor@example.com");

        // Ожидаемая сущность (во что должен преобразоваться DTO)
        Customer customer = new Customer();
        customer.setName("Игорь Пупков");
        customer.setPhone("1234567890");
        customer.setEmail("Igor@example.com");

        // Сохранённая сущность (что вернёт репозиторий)
        Customer savedCustomer = new Customer();
        savedCustomer.setId(1L);
        savedCustomer.setName("Игорь Пупков");
        savedCustomer.setPhone("1234567890");
        savedCustomer.setEmail("Igor@example.com");

        // Ожидаемый выходной DTO (что должен вернуть сервис)
        CustomerDTO expectedDTO = new CustomerDTO();
        expectedDTO.setId(1L);
        expectedDTO.setName("Игорь Пупков");
        expectedDTO.setPhone("1234567890");
        expectedDTO.setEmail("Igor@example.com");

        // Настройка mock-объектов
        when(customerRepository.existsByEmail("Igor@example.com")).thenReturn(false);
        // Проверка уникальности email (email свободен)
        when(modelMapper.map(inputDTO, Customer.class)).thenReturn(customer);
        // Преобразование DTO → Entity
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        // Сохранение с возвратом объекта с ID
        when(modelMapper.map(savedCustomer, CustomerDTO.class)).thenReturn(expectedDTO);
        // Преобразование Entity → DTO


        // Вызов метода
        CustomerDTO result = customerService.createCustomer(inputDTO);

        // Проверки
        assertNotNull(result); // Проверяем, что результат не null
        assertEquals(1L, result.getId()); // Проверяем ID
        assertEquals("Игорь Пупков", result.getName()); // Проверяем имя
        assertEquals("Igor@example.com", result.getEmail()); // Проверяем email
        verify(customerRepository, times(1)).save(customer); // Проверяем, что save вызван 1 раз
    }

    // защита от создания клиентов с уже существующим email
    @Test
    public void testCreateCustomer_WithExistingEmail_ShouldThrowException() {
        CustomerDTO inputDTO = new CustomerDTO();
        inputDTO.setEmail("existing@example.com"); // Email, который уже занят

        //Имитируем, что email уже существует в базе
        when(customerRepository.existsByEmail("existing@example.com")).thenReturn(true);

        // при попытке создания будет выброшено исключение
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(inputDTO);
        });
    }

    // корректность получения данных клиента по его ID
    @Test
    public void testGetCustomerById() {
        // Подготовка тестовых данных
        Long customerId = 1L; // Тестовый ID клиента
        Customer customer = new Customer(); // Тестовая сущность клиента
        customer.setId(customerId);
        customer.setName("Игорь Пупков");

        CustomerDTO expectedDTO = new CustomerDTO(); // Ожидаемый DTO
        expectedDTO.setId(customerId);
        expectedDTO.setName("Игорь Пупков");

        // Настройка mock-объектов
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        // "Когда запросят клиента с ID=1, верни нашего тестового клиента"
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(expectedDTO);
        // "При преобразовании Customer в CustomerDTO верни наш ожидаемый DTO"

        // Вызов метода
        CustomerDTO result = customerService.getCustomerById(customerId);

        // Проверки
        assertNotNull(result); // Проверка, что результат не null
        assertEquals(customerId, result.getId()); // Проверка ID
        assertEquals("Игорь Пупков", result.getName()); // Проверка имени
        verify(customerRepository, times(1)).findById(customerId); // Проверка вызова репозитория
    }

    // проверки случая, когда запрашиваемый клиент не найден в базе данных
    @Test
    public void testGetCustomerById_WhenNotFound_ShouldThrowException() {
        Long customerId = 99L;
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            customerService.getCustomerById(customerId);
        });
    }
}