package src.main.java.HomeWorks.Homework2;

import java.util.Scanner;

/*
Задача 2. Напишите программу на Java, которая принимает два целых
числа от пользователя, а затем печатает сумму, разницу, произведение, среднее
значение, расстояние (разница между целыми числами), максимум (большее из
двух целых чисел), минимум (меньшее из двух целых чисел).
Тестовые данные:
Введите 1-е целое число: 25
Введите второе целое число: 5
Ожидаемый результат :
Сумма двух целых чисел: 30
Разница двух целых чисел: 20
Произведение из двух целых чисел: 125
Среднее из двух целых чисел: 15,00
Расстояние двух целых чисел: 20
Максимальное целое число: 25
Минимальное целое число: 5
 */
public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Введите 1-е целое число: ");
        int firstNumber = scanner.nextInt();
        System.out.println(" Введите 2-е целое число: ");
        int secondNumber = scanner.nextInt();

        //Рассчеты:
        int sum = firstNumber + secondNumber;
        int difference = firstNumber - secondNumber;
        int multiply = firstNumber * secondNumber;
        double average = (firstNumber + secondNumber) / 2.0; // Среднее значение может быть double
        int distance = Math.abs(difference); // Расстояние
        int max = Math.max(firstNumber, secondNumber); // Максимум
        int min = Math.min(firstNumber, secondNumber); // Минимум

        // Вывод результатов:
        System.out.println("Сумма двух целых чисел: " + sum);
        System.out.println("Разница двух целых чисел: " + difference);
        System.out.println("Произведение из двух целых чисел: " + multiply);
        System.out.println("Среднее из двух целых чисел: " + average);
        System.out.println("Расстояние двух целых чисел: " + distance);
        System.out.println("Максимальное целое число: " + max);
        System.out.println("Минимальное целое число: " + min);

    }

}
