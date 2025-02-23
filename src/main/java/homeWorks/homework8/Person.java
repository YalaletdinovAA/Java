package src.main.java.homeworks.homework8;

import java.util.ArrayList;
import java.util.List;
import src.main.java.homeworks.homework8.Product;

public class Person {
    private String name;
    private double money;
    private List<Product> basket = new ArrayList<>();

    public Person(String name, double money) {
        if (name == null || name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            this.name = "";
        } else {
            this.name = name;
        }

        if (money < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            this.money = 0;
        } else {
            this.money = money;
        }

        this.basket = new ArrayList<>();
    }


    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public List<Product> getBasket() {
        return basket;
    }

    // Метод для покупки продукта
    public void addProduct(Product product) {
        if (money >= product.getPrice()) {
            basket.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" - ");
        if (basket.isEmpty()) {
            sb.append("Ничего не куплено");
        } else {
            for (Product p : basket) {
                sb.append(p.getName()).append(", ");
            }
            sb.setLength(sb.length() - 2); // Удаление последней запятой и пробела
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Double.compare(person.money, money) == 0 && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + Double.hashCode(money);
    }
}
