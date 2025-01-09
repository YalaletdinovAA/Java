package src.main.java.homeworks.homework7;

import src.main.java.homeworks.homework7.product.DiscountProduct;
import src.main.java.homeworks.homework7.product.Product;

import java.io.*;
import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Product product = new Product(100,"Молоко");
        DiscountProduct discountProduct= new DiscountProduct(250,"Колбаса",10, LocalDate.of(2025,01,20),true);

        System.out.println(product.toString());
        System.out.println(discountProduct.toString());




    }
}
