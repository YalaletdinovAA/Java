package src.homeWorks.HomeWork7.person;

import src.homeWorks.HomeWork7.product.DiscountProduct;

import java.time.LocalDate;

public class Person {
    private String name;
    private int money;
    private DiscountProduct[] basket = new DiscountProduct[10];

    private int index = 0;

    public void addProductToBasket(DiscountProduct product) {
        if(LocalDate.now().isBefore(product.getDiscauntDate()))
            if (this.money > product.getPrice() / product.getDiscaunt() * 100) {
            this.basket[index] = product;
            index++;
        }else{
            System.out.println("Пользователь не может позволить себе продукт.");
        }
    }
}
