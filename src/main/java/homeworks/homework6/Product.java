package src.main.java.homeworks.homework6;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        if (name == null || name.isEmpty()) {
            System.out.println("Название продукта не может быть пустым");
            this.name = "";
        } else {
            this.name = name;
        }

        if (price < 0) {
            System.out.println("Стоимость продукта не может быть отрицательной");
            this.price = 0;
        } else {
            this.price = price;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
