package homeworks.homework13.utils;

import homeworks.homework13.exceptions.ParseCountException;

public class ParseUtils {

    public static Integer parseCount(String stringInt) {
        try {
            return Integer.parseInt(stringInt);
        } catch (NumberFormatException e) {
            throw new ParseCountException("Невалидное значение");
        }
    }

    // Метод для валидации целых чисел
    public static int validateCount(String input) {
        try {
            return parseCount(input);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            throw e; // Перебрасываем исключение дальше, если нужно
        }
    }

    // Метод для парсинга дробных чисел
    public static double parseNumber(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Невалидное значение");
        }
    }

    // Метод для валидации дробных чисел
    public static double validateNumber(String input) {
        try {
            return parseNumber(input);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            throw e; // Перебрасываем исключение дальше, если нужно
        }
    }

}
