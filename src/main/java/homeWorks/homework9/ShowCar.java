package src.main.java.homeworks.homework9;

//ShowCar – спортивная машина. Looking cool there, bro.
// Включает поле stars (int). (по умолчанию – 0), поле для оценки популярности автомобиля.

import java.util.Objects;

public class ShowCar extends Car {
    private int stars;

    public ShowCar() {

        this.stars = 0;
    }

    public ShowCar(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, year, horsepower, acceleration, suspension, durability);
        this.stars = 0;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "ShowCar{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", horsepower=" + getHorsepower() +
                ", acceleration=" + getAcceleration() +
                ", suspension=" + getSuspension() +
                ", durability=" + getDurability() +
                ", stars=" + stars +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ShowCar showCar = (ShowCar) o;
        return stars == showCar.stars;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), stars);
    }
}