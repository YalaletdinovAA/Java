package repositories;

import cars.Car;
import java.util.List;
import java.util.Optional;

public interface CarRepositoryFile {
    void save(Car car);
    Optional<Car> findById(String id);
    List<Car> findAll();
    boolean delete(String id);
    Car update(Car car);
    void saveAll(List<Car> cars);
}