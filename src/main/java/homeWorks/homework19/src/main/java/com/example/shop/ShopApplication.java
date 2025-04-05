package com.example.shop;

import com.example.shop.model.Customer;
import com.example.shop.model.Order;
import com.example.shop.repository.CustomerRepository;
import com.example.shop.repository.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class ShopApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Autowired
    private Flyway flyway;


    public ShopApplication(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        flyway.migrate();

        System.out.println("=== Демонстрация работы репозиториев ===");

        // Работа с CustomerRepository
        demoCustomerRepository();

        // Работа с OrderRepository (включая фильтрацию по дате)
        demoOrderRepository();
    }

    private void demoCustomerRepository() {
        System.out.println("\n--- CustomerRepository ---");

        // Получение всех покупателей
        List<Customer> customers = customerRepository.findAll();
        System.out.println("Все покупатели:");
        customers.forEach(System.out::println);

        // Получение покупателя по ID
        Long customerId = customers.get(0).getId();
        System.out.println("\nПокупатель с ID " + customerId + ":");
        customerRepository.findById(customerId).ifPresent(System.out::println);

        // Удаление всех покупателей
         customerRepository.deleteAll();
         System.out.println("\nВсе покупатели удалены");
         System.out.println("Текущее количество: " + customerRepository.findAll().size());
    }

    private void demoOrderRepository() {
        System.out.println("\n--- OrderRepository ---");

        // Получение всех заказов
        List<Order> orders = orderRepository.findAll();
        System.out.println("Все заказы:");
        orders.forEach(System.out::println);

        // Получение заказов по конкретной дате
        LocalDateTime targetDate = LocalDateTime.of(2023, 3, 5, 0, 0);
        System.out.println("\nЗаказы за " + targetDate.toLocalDate() + ":");
        orderRepository.findByDate(targetDate).forEach(System.out::println);

        // Удаление всех заказов
          orderRepository.deleteAll();
         System.out.println("\nВсе заказы удалены");
         System.out.println("Текущее количество: " + orderRepository.findAll().size());
    }
}