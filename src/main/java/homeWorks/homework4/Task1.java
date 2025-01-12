package src.main.java.homeworks.homework4;
import java.util.Scanner;
/*
 Задача 1. Для введенной с клавиатуры буквы английского алфавита
 нужно вывести слева стоящую букву на стандартной клавиатуре. При этом
 клавиатура замкнута, т.е. справа от буквы «p» стоит буква «a», а слева от "а"
 буква "р", также соседними считаются буквы «l» и буква «z», а буква «m» с
 буквой «q».
 Входные данные: строка входного потока содержит один символ —
 маленькую букву английского алфавита.
 Выходные данные: следует вывести букву стоящую слева от заданной
 буквы, с учетом замкнутости клавиатуры.
 */
public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите маленькую английскую букву: ");

        // Ввод с консоли
        char ch = scanner.nextLine().charAt(0);

        // Строка букв в клавиатуре
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        // Индекс введенной буквы
        int index = keyboard.indexOf(ch);

        // Находим индекс буквы слева с учетом замкнутости
        int leftIndex = (index - 1 + keyboard.length()) % keyboard.length();

        // Вывод буквы слева
        System.out.println("Буква слева: " + keyboard.charAt(leftIndex));

    }
}
