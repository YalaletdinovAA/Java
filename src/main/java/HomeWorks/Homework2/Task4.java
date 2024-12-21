package src.main.java.HomeWorks.Homework2;

import java.util.Scanner;

/*
Задача 4*. Напишите программу на Java для печати сетки из заданных элементов.
Тестовые данные:
Введите число строк и столбцов сетки: 10
Введите повторяемый элемент сетки:-
Ожидаемый результат :
Сетка по запросу 10 на 10
 */
public class Task4 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите число строк и столбцов сетки:");
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();

        System.out.println("Введите повторяемый элемент сетки: ");
        String element = scanner.next();

        System.out.println("Сетка по запросу " + rows + " на " + columns + ":");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
