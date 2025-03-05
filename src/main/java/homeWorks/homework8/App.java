package src.main.java.homeworks.homework8;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {

        String inputFile = "src/main/java/homeworks/homework8/input.txt"; // Путь к входному файлу
        String outputFile = "src/main/java/homeworks/homework8/output.txt"; // Путь к выходному файлу


        List<src.main.java.homeworks.homework8.Person> customers = new ArrayList<>();
        Map<String, src.main.java.homeworks.homework8.Product> productCatalog = new HashMap<>();
        List<String> actions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            // Чтение данных о покупателях
            line = reader.readLine();
            if (line != null) {
                String[] customersData = line.split(";");
                for (String customerData : customersData) {
                    customerData = customerData.trim();
                    String[] details = customerData.split("=");
                    if (details.length != 2) {
                        System.out.println("Некорректные данные покупателя: " + customerData);
                        continue;
                    }
                    String name = details[0].trim();
                    try {
                        double money = Double.parseDouble(details[1].trim());
                        customers.add(new src.main.java.homeworks.homework8.Person(name, money));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка преобразования денег: " + customerData);
                    }
                }
            }

            // Чтение данных о продуктах
            line = reader.readLine();
            if (line != null) {
                String[] productData = line.split(";");
                for (String productInfo : productData) {
                    productInfo = productInfo.trim();
                    String[] details = productInfo.split("=");
                    if (details.length != 2) {
                        System.out.println("Некорректные данные продукта: " + productInfo);
                        continue;
                    }
                    String name = details[0].trim();
                    try {
                        double price = Double.parseDouble(details[1].trim());
                        productCatalog.put(name, new src.main.java.homeworks.homework8.Product(name, price));
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка преобразования цены: " + productInfo);
                    }
                }
            }

            // Чтение действий
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("END")) {
                    break;
                }
                actions.add(line);
            }

            // Обработка действий
            for (String action : actions) {
                String[] details = action.split(" ");
                if (details.length < 2) {
                    System.out.println("Некорректная строка действия: " + action);
                    continue;
                }
                String customerName = details[0] + " " + details[1];
                String productName = details.length > 2 ? action.substring(customerName.length()).trim() : "";

                src.main.java.homeworks.homework8.Person customer = customers.stream()
                        .filter(c -> c.getName().equals(customerName))
                        .findFirst()
                        .orElse(null);
                src.main.java.homeworks.homework8.Product product = productCatalog.get(productName);

                if (customer != null && product != null) {
                    customer.addProduct(product);
                } else {
                    System.out.println("Действие невозможно: " + action);
                }
            }

            // Запись результатов в файл
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                for (src.main.java.homeworks.homework8.Person customer : customers) {
                    writer.write(customer.toString());
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    /*public static void main(String[] args) {
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

*/