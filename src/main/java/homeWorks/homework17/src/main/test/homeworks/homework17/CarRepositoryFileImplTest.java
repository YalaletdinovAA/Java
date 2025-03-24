package homeworks.homework17;

import cars.Car;
import cars.PerformanceCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.impl.CarRepositoryFileImpl;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryFileImplTest {
    private CarRepositoryFileImpl repository;

    @BeforeEach
    void setUp() {
        repository = new CarRepositoryFileImpl("src/main/java/homeworks/homework17/src/main/test/resources/test_cars.txt");
        repository.deleteAll(); // Очищаем данные перед каждым тестом
    }

    @Test
    void testCreateAndFindById() {
        Car car = new Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        repository.create(car);

        Car foundCar = repository.findById(car.getId());
        assertNotNull(foundCar);
        assertEquals(car.getBrand(), foundCar.getBrand());
        assertEquals(car.getModel(), foundCar.getModel());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        Car car2 = new PerformanceCar("Ferrari", "488", 2021, 700, 3, 10, 200);
        repository.create(car1);
        repository.create(car2);

        List<Car> cars = repository.findAll();
        assertEquals(2, cars.size());
    }

    @Test
    void testUpdate() {
        Car car = new Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        repository.create(car);

        car.setModel("Camry");
        repository.update(car);

        Car updatedCar = repository.findById(car.getId());
        assertEquals("Camry", updatedCar.getModel());
    }

    @Test
    void testDeleteById() {
        Car car = new Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        repository.create(car);

        repository.deleteById(car.getId());
        Car deletedCar = repository.findById(car.getId());
        assertNull(deletedCar);
    }

    @Test
    void testDeleteAll() {
        Car car1 = new Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        Car car2 = new PerformanceCar("Ferrari", "488", 2021, 700, 3, 10, 200);
        repository.create(car1);
        repository.create(car2);

        repository.deleteAll();
        List<Car> cars = repository.findAll();
        assertTrue(cars.isEmpty());
    }

}