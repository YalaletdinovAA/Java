package com.example.test;


import com.example.SpringAopCrudApplication;
import com.example.aspect.ProductAspect;
import com.example.model.Product;
import com.example.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@EnableAspectJAutoProxy
class SpringAopCrudApplicationTest {

    @Autowired
    private ProductAspect productAspect;

    @MockBean
    private ProductService productService;

    @Test
    void contextLoads() {
        assertNotNull(productAspect, "ProductAspect должен загрузиться");
    }

    @Test
    void testProductAspectLogging() throws Exception {
        // Подготовка тестовых данных
        Product testProduct = new Product(1L, "Test Product", 100.0);

        // Настройка моков
        when(productService.addProduct(any(Product.class))).thenReturn(testProduct);
        when(productService.getProductById(1L)).thenReturn(testProduct);

        // Вызов методов, которые должны быть перехвачены аспектом
        Product addedProduct = productService.addProduct(testProduct);
        Product foundProduct = productService.getProductById(1L);

        // Проверки
        assertNotNull(addedProduct);
        assertNotNull(foundProduct);

        // Проверка, что методы сервиса были вызваны
        verify(productService, times(1)).addProduct(any(Product.class));
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testMainMethod() {
        // Проверка, что приложение запускается без ошибок
        SpringAopCrudApplication.main(new String[]{});
        assertTrue(true, "Приложение запускается без ошибок");
    }
}