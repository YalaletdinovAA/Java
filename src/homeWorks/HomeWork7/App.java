package src.homeWorks.HomeWork7;

import src.homeWorks.HomeWork7.product.DiscountProduct;

import src.homeWorks.HomeWork7.product.Product;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Product product = new Product(100,"Молоко");
        DiscountProduct discountProduct= new DiscountProduct(250,"Колбаса",10, LocalDate.of(2025,01,20),true);

        System.out.println(product.toString());
        System.out.println(discountProduct.toString());
    }
}
