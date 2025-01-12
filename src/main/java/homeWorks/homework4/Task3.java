package src.main.java.homeworks.homework4;
import java.util.Arrays;
/*
Задача3*.Задана строка,состоящая из букв английского алфавита,
разделенных одним пробелом.
Необходимо каждую последовательность символов упорядочить по возрастанию
и вывести слова в нижнем регистре.
Входные данные:в единственной строке последовательность символов представляющее два слова.
Выходные данные: упорядоченные по возрастанию буквы в нижнем регистре.
 */
public class Task3 {
    public static void main(String[] args) {

        String input = "karabas barabas";
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sortedString = new String(charArray);
        System.out.println(sortedString);

    }
}
