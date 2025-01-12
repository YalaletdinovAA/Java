package src.main.java.homeworks.homework4;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*Задача 2.
Задана последовательность, состоящая только из символов ‘>’,
 ‘<’ и ‘-‘. Требуется найти количество стрел, которые спрятаны в этой
 последовательности. Стрелы– это подстроки вида ‘>>-->’ и ‘<--<<’.
 Входные данные: в первой строке входного потока записана строка,
 состоящая из символов ‘>’, ‘<’ и ‘-‘ (без пробелов). Строка может содержать до
 106 символов.
 Выходные данные: в единственную строку выходного потока нужно
 вывести искомое количество стрелок.

 */


public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку состоящую из символов ‘>’, ‘<’ и ‘-‘ (без пробелов):");
        String input = scanner.nextLine();

        // Регулярное выражение для поиска стрел
        String regex = ">>-->|<--<<";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        int count = 0;

        // Находим все совпадения
        while (matcher.find()) {
            count++;
        }

        System.out.println(count);
    }
}