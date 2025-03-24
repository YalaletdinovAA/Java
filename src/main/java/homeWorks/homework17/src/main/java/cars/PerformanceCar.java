package cars;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PerformanceCar extends Car {
    private List<String> addOns;


   // public PerformanceCar() {
    //}

    public PerformanceCar(String brand, String model, int year, int horsepower, int acceleration, int suspension, int durability) {
        super(brand, model, year, (int) (horsepower * 1.5), acceleration, (int) (suspension * 0.75), durability);
        this.addOns = new ArrayList<>();
    }


    public List<String> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<String> addOns) {
        this.addOns = addOns;
    }

    public void addAddOn(String addOn) {
        this.addOns.add(addOn);
    }
/*
    @Override
    public String toString() {
        return "PerformanceCar{" +
                "brand='" + getBrand() + '\'' +
                ", model='" + getModel() + '\'' +
                ", year=" + getYear() +
                ", horsepower=" + getHorsepower() +
                ", acceleration=" + getAcceleration() +
                ", suspension=" + getSuspension() +
                ", durability=" + getDurability() +
                ", addOns=" + addOns +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PerformanceCar that = (PerformanceCar) o;
        return Objects.equals(addOns, that.addOns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addOns);
    }*/
}