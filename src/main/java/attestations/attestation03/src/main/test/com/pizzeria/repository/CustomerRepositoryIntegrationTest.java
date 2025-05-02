package com.pizzeria.repository;

import com.pizzeria.model.Customer;
import com.pizzeria.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository customerRepository;

    // Проверяет, что метод findById() корректно находит существующего клиента в БД
    @Test
    public void whenFindById_thenReturnCustomer() {
        // Создаем тестовые данные
        Customer customer = new Customer();
        customer.setName("Petr");
        customer.setPhone("123");
        customer.setAddress("Address");
        entityManager.persistAndFlush(customer);//сохраняет его в тестовую БД и сразу синхронизирует изменения

        Customer found = customerRepository.findById(customer.getId()).orElse(null);
        //Ищет клиента по его id (который был сгенерирован при сохранении через entityManager)

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo(customer.getName());
        //Проверяет, что имя найденного клиента совпадает с исходным
    }

    //Проверяет, что метод findById() возвращает null для несуществующего ID
    @Test
    public void whenInvalidId_thenReturnNull() {
        Customer fromDb = customerRepository.findById(-1L).orElse(null);
        assertThat(fromDb).isNull();
    }
    // интеграционные тесты работают с реальной БД (пусть и тестовой, например, H2).
    //В отличие от unit-тестов, где репозиторий мокируется, здесь проверяется именно взаимодействие с БД.
}