package src.main.java.homeworks.homework2;
/*
Задача 3*.
Напишите Java-программу для объединения данной строки ссамим собой заданное количество раз.
Тестовые данные:Исходная строка: JavaСколько раз вывести строку? 8
Ожидаемый результат :
После повторения 8 раз: JavaJavaJavaJavaJavaJavaJavaJava
 */

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Исходная строка:");
        String inputString = scanner.nextLine();
        System.out.println("Сколько раз вывести строку?");
        int repeatCount = scanner.nextInt();

        String result = "";
        for (int i = 0; i < repeatCount; i++) {
            result += inputString;
        }
        System.out.println("После повторения " + repeatCount + " раз: " + result);
    }
}

