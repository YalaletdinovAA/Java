package src.main.java.homeworks.homework6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            System.out.println("Введите имя покупателя : ");
            String name = scanner.next();
            System.out.println("Введите количество денег : ");
            int money = scanner.nextInt();
            Person person = new Person(name, money);
            persons.add(person);
        }

        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("Введите название продукта : ");
            String name = scanner.next();
            System.out.println("Введите стоимость : ");
            int price = scanner.nextInt();
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            products.add(product);
        }


        System.out.println("Начинаем покупку : ");

            while (true) {

                System.out.println("Введите имя покупателя: ");
                String name = scanner.next();

                if (name.equals("END")) {
                    break;
                }

                for (Person person: persons){
                    if(person.getName().equals(name)){
                        System.out.println("Введите название продукта: ");
                        String nameProduct = scanner.next();

                        for (Product product: products){
                            if(product.getName().equals(nameProduct)){
                                person.addProductToBasket(product);
                            }
                        }
                    }
                }
            }

        for(Person person :persons){
            System.out.println(person);
        }
    }
}
