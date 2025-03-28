package cars;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Car {

    private String id; // Уникальный идентификатор автомобиля
    private String brand;
    private String model;
    private int year;
    private int horsepower;
    private int acceleration;
    private int suspension;
    private int durability;



    public Car(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability) {
        this.id = UUID.randomUUID().toString(); // Генерация уникального ID
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.suspension = suspension;
        this.durability = durability;
    }
    /*

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getSuspension() {
        return suspension;
    }

    public void setSuspension(int suspension) {
        this.suspension = suspension;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", horsepower=" + horsepower +
                ", acceleration=" + acceleration +
                ", suspension=" + suspension +
                ", durability=" + durability +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
                horsepower == car.horsepower &&
                acceleration == car.acceleration &&
                suspension == car.suspension &&
                durability == car.durability &&
                brand.equals(car.brand) &&
                model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, horsepower, acceleration, suspension, durability);
    }*/
}
