package src.main.java.homeworks.homework9;

import java.util.ArrayList;
import java.util.List;

//Garage – место, где остаются все автомобили, когда они не участвуют в гонках.
// Гараж   также   предоставляет   возможность   модифицировать припаркованный автомобиль.
// Включает parkedCars (массив объектов типа Car).

public class Garage {
    private List<src.main.java.homeworks.homework9.Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
    }

    public List<src.main.java.homeworks.homework9.Car> getParkedCars() {
        return parkedCars;
    }

    public void setParkedCars(List<src.main.java.homeworks.homework9.Car> parkedCars) {
        this.parkedCars = parkedCars;
    }

    // Добавление автомобиля в гараж
    public void parkCar(src.main.java.homeworks.homework9.Car car) {
        this.parkedCars.add(car);
    }

    // Удаление автомобиля из гаража
    public void unparkCar(src.main.java.homeworks.homework9.Car car) {
        this.parkedCars.remove(car);
    }

    @Override
    public String toString() {
        return "Garage{" +
                "parkedCars=" + parkedCars +
                '}';
    }
}