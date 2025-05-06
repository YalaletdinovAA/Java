package com.pizzeria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.controller.CustomerController;
import com.pizzeria.dto.CustomerDTO;
import com.pizzeria.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();//Создаём тестовый объект
        customerDTO.setName("Petr");
        customerDTO.setPhone("123");

        Mockito.when(customerService.createCustomer(any())).thenReturn(customerDTO);
        // Mockito подменяет реальный customerService на заглушку
        // customerService.createCustomer() с любыми аргументами (any()), вернуть наш тестовый customerDTO

        mockMvc.perform(post("/api/customers")//Эндпоинт POST принимает JSON с данными клиента
                        .contentType(MediaType.APPLICATION_JSON)//Устанавливается заголовок Content-Type:application/json
                        .content(objectMapper.writeValueAsString(customerDTO)))//JSON, преобразованный из customerDTO (используется objectMapper)
                .andExpect(status().isCreated())//проверяет, что статус HTTP-ответа равен 201 Created (успешное создание ресурса)
                .andExpect(jsonPath("$.name").value("Petr"));
        //$ - корень JSON-документа.
        //.name - обращение к полю name.
    }
}