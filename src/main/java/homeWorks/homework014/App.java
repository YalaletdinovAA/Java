package src.main.java.homeworks.homework014;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Person[] people = new Person[5];
        Product[] products = new Product[7];

        // Инициализация продуктов
        products[0] = new Product("Хлеб", 40);
        products[1] = new Product("Молоко", 60);
        products[2] = new Product("Торт", 1000);
        products[3] = new Product("Кофе растворимый", 879);
        products[4] = new Product("Масло", 150);
        products[5] = new Product("Мороженое",200);
        products[6] = new Product("Макароны",800);


        // Инициализация покупателей
        people[0] = new Person("Павел Андреевич", 10000);
        people[1] = new Person("Анна Петровна", 2000);
        people[2] = new Person("Борис", 10);
        people[3] = new Person("Женя", 0);
        people[4] = new Person("Света", 3);

        // Цикл покупки продуктов
        for (Person person : people) {
            System.out.println(person.getName() + " - Начинает покупки");
            while (true) {
                System.out.print("Введите продукт (или END для завершения): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("END")) break;

                Product selectedProduct = null;
                for (Product product : products) {
                    if (product.getName().equalsIgnoreCase(input)) {
                        selectedProduct = product;
                        break;
                    }
                }

                if (selectedProduct != null) {
                    person.buyProduct(selectedProduct);
                } else {
                    System.out.println("Продукт не найден!");
                }
            }
            System.out.println(person); // Выводим результаты покупок
        }

        scanner.close();
    }
}
