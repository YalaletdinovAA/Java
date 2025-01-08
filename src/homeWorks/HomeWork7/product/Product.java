package src.homeWorks.HomeWork7.product;
/*
 Задача 1. Расширить программу прошлого домашнего задания.
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
 */

import java.util.Objects;
public class Product {
private String name;
private Integer price;

 public Product(Integer price, String name) {
  this.price = price;
  this.name = name;
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  if (name == null || name.trim().isEmpty()) {
   System.out.println("Название продукта не может быть пустым.");
   return;
  }
  if (name.length() < 3) {
   System.out.println("Название продукта должно быть не короче 3 символов.");
   return;
  }
  if (name.matches(".*\\d.*")) {
   System.out.println("Название продукта не может содержать цифры.");
   return;
  }
  this.name = name;
 }

 public Integer getPrice() {
  return price;
 }

 public void setPrice(Integer price) {
  if (price <= 0) {
   System.out.println("Стоимость продукта не может быть 0 или отрицательной.");
   return;
  }
  this.price = price;
 }

 @Override
 public boolean equals(Object o) {
  if (this == o) return true;
  if (o == null || getClass() != o.getClass()) return false;
  Product product = (Product) o;
  return Objects.equals(name, product.name) && Objects.equals(price, product.price);
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
