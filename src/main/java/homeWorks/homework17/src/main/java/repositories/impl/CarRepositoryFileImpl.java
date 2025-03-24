package repositories.impl;


import cars.Car;
import repositories.CarRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class CarRepositoryFileImpl implements CarRepository {
    private static final String FILE_PATH = "src/main/java/homeworks/homework17/src/main/resources/cars.txt";

    public CarRepositoryFileImpl(String s) {
        ensureFileExists();
    }

    @Override
    public void create(Car car) {
        List<Car> cars = findAll();
        cars.add(car);
        saveCarsToFile(cars);
    }

    @Override
    public Car findById(String id) {
        return findAll().stream()
                .filter(car -> car.getId().equals(id)) // Исправлено: сравниваем по id
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Машина не найдена"));
    }

    @Override
    public List<Car> findAll() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    cars.add(parseCar(line));
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка при обработке строки: " + line);
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
        return cars;
    }

    @Override
    public void update(Car car) {
        List<Car> cars = findAll();
        cars.removeIf(c -> c.getModel().equals(car.getModel()));
        cars.add(car);
        saveCarsToFile(cars);
    }

    @Override
    public void deleteById(String id) {
        List<Car> cars = findAll();
        cars.removeIf(car -> car.getModel().equals(id));
        saveCarsToFile(cars);
    }

    @Override
    public void deleteAll() {
        saveCarsToFile(new ArrayList<>());
    }

    private void saveCarsToFile(List<Car> cars) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Car car : cars) {
                writer.write(car.getBrand() + "|" +
                        car.getModel() + "|" +
                        car.getYear() + "|" +
                        car.getHorsepower() + "|" +
                        car.getAcceleration() + "|" +
                        car.getSuspension() + "|" +
                        car.getDurability());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    private void ensureFileExists() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                if (!file.createNewFile()) {
                    throw new IOException("Не удалось создать файл: " + FILE_PATH);
                }
            } catch (IOException e) {
                System.err.println("Ошибка при создании файла: " + e.getMessage());
                throw new RuntimeException("Ошибка создания файла", e);
            }
        }
    }

    private Car parseCar(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 7) {
            throw new IllegalArgumentException("Некорректный формат строки: " + line);
        }
        return new Car(
                parts[0],
                parts[1],
                Integer.parseInt(parts[2]),
                Integer.parseInt(parts[3]),
                Integer.parseInt(parts[4]),
                Integer.parseInt(parts[5]),
                Integer.parseInt(parts[6])
        );
    }
}