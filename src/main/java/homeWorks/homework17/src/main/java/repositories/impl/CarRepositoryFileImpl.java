package repositories.impl;

import cars.Car;
import repositories.CarRepositoryFile;
import java.io.*;
import java.util.*;

public class CarRepositoryFileImpl implements CarRepositoryFile {
    private final File file;
    private final Map<String, Car> cars = new HashMap<>();

    public CarRepositoryFileImpl(String filename) {
        this.file = new File(filename);
        loadFromFile();
    }

    @Override
    public void save(Car car) {
        String id = generateId(car);
        cars.put(id, car);
        saveToFile();
    }

    @Override
    public Optional<Car> findById(String id) {
        return Optional.ofNullable(cars.get(id));
    }

    @Override
    public List<Car> findAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public boolean delete(String id) {
        boolean removed = cars.remove(id) != null;
        if (removed) saveToFile();
        return removed;
    }

    @Override
    public Car update(Car car) {
        String id = generateId(car);
        if (cars.containsKey(id)) {
            cars.put(id, car);
            saveToFile();
            return car;
        }
        return null;
    }

    @Override
    public void saveAll(List<Car> cars) {
        cars.forEach(this::save);
    }

    private String generateId(Car car) {
        return car.getBrand() + ":" + car.getModel() + ":" + car.getYear();
    }

    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            Map<String, Car> loaded = (Map<String, Car>) ois.readObject();
            cars.clear();
            cars.putAll(loaded);
        } catch (FileNotFoundException e) {

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Failed to load cars from file", e);
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(cars);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save cars to file", e);
        }
    }
}