package src.main.java.homeworks.homework10;

public class Main {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // Проверка на четность
        ByCondition isEven = number -> number % 2 == 0;
        int[] evenNumbers = Sequence.filter(numbers, isEven);
        System.out.println("Четные числа: ");
        for (int num : evenNumbers) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Проверка, является ли сумма цифр числа четной
        ByCondition isSumOfDigitsEven = number -> {
            int sum = 0;
            int n = number;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            return sum % 2 == 0;
        };
        int[] numbersWithEvenDigitSum = Sequence.filter(numbers, isSumOfDigitsEven);
        System.out.println("Числа с четной суммой цифр: ");
        for (int num : numbersWithEvenDigitSum) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
