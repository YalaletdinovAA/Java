package src.main.java.homeworks.homework9;

import java.util.ArrayList;
import java.util.List;

//Garage – место, где остаются все автомобили, когда они не участвуют в гонках.
// Гараж   также   предоставляет   возможность   модифицировать припаркованный автомобиль.
// Включает parkedCars (массив объектов типа Car).
import java.util.ArrayList;
import java.util.List;

public class Garage {
    private List<Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    public List<Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    // Добавление автомобиля в гараж
    public void parkCar(Car car) {
        this.parkedCars.add(car);
    }

    // Удаление автомобиля из гаража
    public void unparkCar(Car car) {
        this.parkedCars.remove(car);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "parkedCars=" + parkedCars +
                '}';
    }
}