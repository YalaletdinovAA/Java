package repositories;


import cars.Car;
import lombok.Getter;

import java.util.List;


public interface CarRepository {
    void create(Car car);
    Car findById(String id);
    List<Car> findAll();
    void update(Car car);
    void deleteById(String id);
    void deleteAll();
}