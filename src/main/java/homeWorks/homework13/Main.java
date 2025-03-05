package homeworks.homework13;

import static homeworks.homework13.utils.ParseUtils.validateCount;
import static homeworks.homework13.utils.ParseUtils.validateNumber;

public class Main {
    public static void main(String[] args) {

        try {
            int count = validateCount("123");
            System.out.println("Парсинг целого числа: " + count);

            double number = validateNumber("45.67");
            System.out.println("Парсинг дробного числа: " + number);

            // Пример с ошибкой
            int invalidCount = validateCount("15");
        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
