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

        try {
            String string;
            FileReader fileReader = new FileReader("src/main/java/homeworks/homework8/input.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("src/main/java/homeworks/homework8/output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            while (bufferedReader.ready()) {
                string = bufferedReader.readLine();
                bufferedWriter.write(string + "\r\n");
            }
            bufferedReader.close();
            fileReader.close();
            bufferedWriter.flush();
            bufferedWriter.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }
}
