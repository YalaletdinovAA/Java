package homeworks.homework14;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testProductWithValidParameters() {
        Product product = new Product("Молоко", 80.0);
        assertEquals("Молоко", product.getName());
        assertEquals(80.0, product.getPrice());
    }


    @Test
    void testProductWithNegativePrice() {
        Product product = new Product("Яблоко", -100.0);
        assertEquals(0, product.getPrice());
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.0, 1.0, 100.0, 999.99})
    void testSetPriceWithValidValues(double price) {
        Product product = new Product();
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }

    @Disabled("Этот тест отключен для демонстрации")
    @Test
    void testDisabled() {
        Product product = new Product("Disabled", 0.0);
        assertEquals("Disabled", product.getName());
    }
}