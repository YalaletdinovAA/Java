package src.main.java.homeworks.homework7.product;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        } else if (name.length() < 3) {
            throw new IllegalArgumentException("Название продукта должно содержать не менее 3 символов.");
        } else if (name.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Название продукта не должно содержать цифры.");
        } else {
            this.name = name;
        }

        if (price < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
        } else {
            this.price = price;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
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
