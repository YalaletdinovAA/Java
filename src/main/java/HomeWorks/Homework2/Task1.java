package src.main.java.HomeWorks.Homework2;
/*
Задача 1. Напишите Java-программу для преобразования температуры из
Фаренгейта в градусы Цельсия.
Тестовые данные:
Введите степень в градусах Фаренгейта: 212
Ожидаемый результат :
212.0 градусов по Фаренгейту равна 100.0 по Цельсию
package HomeWork2;
 */

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите степень в градусах Фаренгейта: ");
        double fahrengeit = scanner.nextDouble();
        // Используем формулу преобразования температуры по Фаренгейту в Цельсий
        //C = (F – 32) × (5/9)
        double celcius = (fahrengeit - 32) * 5 / 9;
        System.out.println("Ответ: " + fahrengeit + " градусов по Фаренгейту равна " + celcius + " по Цельсию.");
    }
}
