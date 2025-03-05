package homeworks.homework14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testAddPersonAndAmountMoney() {
        Person person = new Person("Иван", 100.0);
        assertEquals("Иван", person.getName());
        assertEquals(100.0, person.getMoney());
        assertTrue(person.getBasket().isEmpty());
    }

    @Test
    void testBuyProduct() {
        Person person = new Person("Илья", 50.0);
        Product product = new Product("Книга", 30.0);

        person.buyProduct(product);

        assertEquals(1, person.getBasket().size());
        assertEquals(20.0, person.getMoney());
        assertEquals("Книга", person.getBasket().get(0).getName());
    }

    @Test
    void testNegativeMoney() {
        Person person = new Person("Ярослав", -100.0);
        assertEquals(0, person.getMoney());
    }


    @ParameterizedTest
    @CsvSource({
            "Ильдар, 100.0, Книга, 30.0, 70.0",
            "Самир, 50.0, Ручка, 10.0, 40.0",
    })
    void testBuyProductWithParameters(String name, double money, String productName, double productPrice, double expectedMoney) {
        Person person = new Person(name, money);
        Product product = new Product(productName, productPrice);

        person.buyProduct(product);

        assertEquals(1, person.getBasket().size());
        assertEquals(expectedMoney, person.getMoney());
        assertEquals(productName, person.getBasket().get(0).getName());
    }
}