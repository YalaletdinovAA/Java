package src.main.java.homeworks.homework8;

import java.util.Objects;

public class Product {
    private String name;
    private int price;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.isEmpty()) {
            System.out.println("Название продукта не может быть пустой строкой.");
        } else {
            this.name = name;
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0){
            System.out.println("Стоимость продукта не может быть отрицательным числом.");
        }else{
            this.price = price;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price && Objects.equals(name, product.name);
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