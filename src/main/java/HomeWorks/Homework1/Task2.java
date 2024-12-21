package src.main.java.HomeWorks.Homework1;

import java.util.Random;

/* Задача 2*. Вася и Петя играют в игру “Камень, ножницы, бумага”.
 Каждый из них показывает свою фигуру камень-0, ножницы-1, бумага-2.
 Программа определяет, кто из них выиграл.
 Выбор каждого участника формируется случайным образом.

 */
public class Task2 {
    public static void main(String[] args) {
        Random random = new Random();
        int vasyaChoice = random.nextInt(3);
        int petyaChoice = random.nextInt(3);

        if (vasyaChoice == petyaChoice) {
            System.out.println("Ничья!");
        } else if ((vasyaChoice == 0 && petyaChoice == 1)||
        (vasyaChoice == 1 && petyaChoice == 2) ||
                (vasyaChoice == 2 && petyaChoice == 0)){
            System.out.println("Вася выиграл!");
        }else{
            System.out.println("Петя выиграл!");
        }
    }
}
