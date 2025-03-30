import cars.Car;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Garage {
    private List<Car> parkedCars;

    public Garage() {
        this.parkedCars = new ArrayList<>();
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