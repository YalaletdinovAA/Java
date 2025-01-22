package src.main.java.homeworks.homework7.person;

import src.main.java.homeworks.homework7.product.Product;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private double money;
    private List<Product> basket;

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
    public void buyProduct(Product product) {
        if (product != null && money >= product.getPrice()) {
            basket.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else if (product != null) {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", basket=" + basket +
                '}';
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
