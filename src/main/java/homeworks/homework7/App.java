package src.main.java.homeworks.homework7;
/*
 Расширить программу прошлого домашнего задания.
 Добавить механизм наследования.
 В программе должно быть два класса – один для обычных продуктов
Product, а другой для специальных – DiscountProduct.
 • Product - представляет обычный продукт из прошлого домашнего
 задания. Характеристики Продукта: название и стоимость. Название продукта
 не может быть пустой строкой, оно должно быть. Стоимость продукта не может
 быть отрицательным числом.
 • Скидочный продукт — специальный продукт, цена которого снижена на
 размер скидки. У скидки есть также срок действия. После завершения срока
 действия скидка меняется.
 Ограничения в классах для продуктов:- Название продукта не должно содержать только цифры;
 - Если название продукта короче, чем 3 символа, то такое название
 недействительно;-
Если стоимость продукта или скидочного продукта 0 или
 отрицательная, то такая цена неправильная. Должна быть ошибка валидации.
 Программа реализуется в отдельной ветке git homeworks/homework07.
 При сохранении состояния программы (коммиты) пишется сообщение с
 описанием хода работы по задаче.

 Тестовые данные:
 Хлеб = 40
 Молоко = 60
 Торт = 800, 15%
 Кофе растворимый = 432, 50%
 END
 888 = 78
 END
 ен = 78
 END
 Шоколадка = 0
 END

 Ожидаемый результат :
  Обычные продукты: Хлеб, Молоко
 Акционные продукты: Торт, Кофе
 растворимый
 Недопустимое имя продукта!
 Недопустимое имя продукта!
 Недопустимая стоимость продукта!
 */

import src.main.java.homeworks.homework7.person.Person;
import src.main.java.homeworks.homework7.product.DiscountProduct;
import src.main.java.homeworks.homework7.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        List<DiscountProduct> discountProducts = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        double discount = 0;
        while (true) {
            System.out.println("Введите название продукта или 'END' для завершения:");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("END")) {
                break;
            }

            System.out.println("Введите цену продукта:");
            double price = -1;
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Недопустимая стоимость продукта");
                continue;
            }

            // Проверяем, является ли продукт скидочным (по формату данных)
            if (price > 0) {
                System.out.println("Введите тип продукта (обычный/скидочный):");
                String type = scanner.nextLine().trim();

                if (type.equalsIgnoreCase("обычный")) {
                    try {
                        Product product = new Product(name, price);
                        products.add(product);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (type.equalsIgnoreCase("скидочный")) {
                    System.out.println("Введите процент скидки:");
                    discount = -1;
                    try {
                        discount = Double.parseDouble(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Недопустимая скидка");
                        continue;
                    }

                    System.out.println("Введите срок действия скидки (гггг-мм-дд):");
                    String expirationDate = scanner.nextLine().trim();

                    try {
                        DiscountProduct discountProduct = new DiscountProduct(name, price, discount, expirationDate);
                        discountProducts.add(discountProduct);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Недопустимый тип продукта");
                }
            }
        }


        // Вывод обычных продуктов
        System.out.println("\nОбычные продукты:");
        for (Product product : products) {
            System.out.println(product.getName() + " = " + product.getPrice());
        }

        // Вывод акционных продуктов
        System.out.println("\nАкционные продукты:");
        for (DiscountProduct discountProduct : discountProducts) {
            System.out.println(discountProduct.getName() + " = " + discountProduct.getPrice() +
                    " Скидка: " + discountProduct.getDiscount() + "%, Срок действия: " + discountProduct.getDiscountExpiryDate());
        }

        scanner.close();
    }
}

    /*   public static void main(String[] args) {
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
}*/
