package utils;

public class InputUtils {

    // Функция для парсинга строки в целое число
    public static int parseCount(String input) throws IllegalArgumentException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }

    // Функция для валидации и парсинга строки в целое число
    public static String validateCount(String input) {
        try {
            int result = parseCount(input);
            return "Результат: " + result;
        } catch (IllegalArgumentException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    // Пример использования
    public static void main(String[] args) {
        String testInput1 = "123";
        String testInput2 = "abc";

        System.out.println(validateCount(testInput1)); // Результат: 123
        System.out.println(validateCount(testInput2)); // Ошибка: Невалидное значение
    }
}