package src.main.java.homeworks.homework11.test;
import src.main.java.homeworks.homework11.model.Car;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Чтение данных из файла cars.txt
        List<Car> cars = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/java/homeworks/homework11/data/cars.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String number = parts[0];
                    String model = parts[1];
                    String color = parts[2];
                    int mileage = Integer.parseInt(parts[3]);
                    long cost = Long.parseLong(parts[4]);
                    cars.add(new Car(number, model, color, mileage, cost));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл cars.txt не найден!");
            return;
        }

        // Вывод всех автомобилей
        System.out.println("Автомобили в базе:");
        System.out.printf("%-8s %-8s %-8s %8s %12s%n", "Number", "Model", "Color", "Mileage", "Cost");
        cars.forEach(System.out::println);

        // Заданные параметры
        String colorToFind = "Black";
        int mileageToFind = 0;
        long n = 700_000L; // Нижняя граница цены
        long m = 800_000L; // Верхняя граница цены
        String modelToFind = "Toyota";

        // 1) Номера автомобилей с заданным цветом или нулевым пробегом
        System.out.println("\nНомера автомобилей по цвету или пробегу:");
        cars.stream()
                .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                .map(Car::getNumber)
                .forEach(System.out::println);

        // 2) Количество уникальных моделей в ценовом диапазоне от n до m
        long uniqueModelsCount = cars.stream()
                .filter(car -> car.getCost() >= n && car.getCost() <= m)
                .map(Car::getModel)
                .distinct()
                .count();
        System.out.println("\nКоличество уникальных моделей в ценовом диапазоне от " + n + " до " + m + ": " + uniqueModelsCount);

        // 3) Цвет автомобиля с минимальной стоимостью
        Optional<String> minCostColor = cars.stream()
                .min(Comparator.comparingLong(Car::getCost))
                .map(Car::getColor);
        minCostColor.ifPresent(color -> System.out.println("\nЦвет автомобиля с минимальной стоимостью: " + color));

        // 4) Средняя стоимость искомой модели
        double averageCost = cars.stream()
                .filter(car -> car.getModel().equals(modelToFind))
                .mapToLong(Car::getCost)
                .average()
                .orElse(0.0);
        System.out.printf("\nСредняя стоимость модели %s: %.2f%n", modelToFind, averageCost);
    }
}