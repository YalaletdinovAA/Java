package homeworks.homework17;

import cars.Car;
import org.junit.jupiter.api.*;
import repositories.impl.CarRepositoryFileImpl;
import java.io.File;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CarRepositoryFileImplTest {
    private static final String TEST_FILE = "test_cars.dat";
    private CarRepositoryFileImpl repository;

    @BeforeEach
    void setUp() {
        repository = new CarRepositoryFileImpl(TEST_FILE);
    }

    @AfterEach
    void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    void testSaveAndFindById() {
        Car car = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        repository.save(car);

        Optional<Car> found = repository.findById("Toyota:Camry:2020");
        assertTrue(found.isPresent());
        assertEquals(car, found.get());
    }

    @Test
    void testFindAll() {
        Car car1 = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        Car car2 = new Car("Honda", "Accord", 2021, 190, 7, 8, 6);

        repository.save(car1);
        repository.save(car2);

        List<Car> cars = repository.findAll();
        assertEquals(2, cars.size());
        assertTrue(cars.contains(car1));
        assertTrue(cars.contains(car2));
    }

    @Test
    void testDelete() {
        Car car = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        repository.save(car);

        boolean deleted = repository.delete("Toyota:Camry:2020");
        assertTrue(deleted);
        assertFalse(repository.findById("Toyota:Camry:2020").isPresent());
    }

    @Test
    void testUpdate() {
        Car original = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        repository.save(original);

        Car updated = new Car("Toyota", "Camry", 2020, 250, 7, 8, 6);
        Car result = repository.update(updated);

        assertEquals(updated, result);
        assertEquals(updated, repository.findById("Toyota:Camry:2020").get());
    }

    @Test
    void testSaveAll() {
        Car car1 = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        Car car2 = new Car("Honda", "Accord", 2021, 190, 7, 8, 6);

        repository.saveAll(List.of(car1, car2));

        assertEquals(2, repository.findAll().size());
    }

    @Test
    void testPersistence() {
        Car car = new Car("Toyota", "Camry", 2020, 200, 8, 7, 5);
        repository.save(car);

        // Create new repository instance to test file loading
        CarRepositoryFileImpl newRepo = new CarRepositoryFileImpl(TEST_FILE);
        Optional<Car> loaded = newRepo.findById("Toyota:Camry:2020");

        assertTrue(loaded.isPresent());
        assertEquals(car, loaded.get());
    }
}