package src.main.java.homeworks.homework8;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private int money;

    public Person(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    private List<Product>products = new ArrayList<>();

    public void addProductToBasket(Product product) {

        if (this.money >= product.getPrice()) {
            this.products.add(product);
            money-=product.getPrice();
            System.out.println(name + " купил " + product.getName());
        }else{
            System.out.println( name + " не может позволить себе " + product.getName());
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", products=" + products +
                '}';
    }
}