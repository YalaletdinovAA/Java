package src.main.java.homeworks.homework3;

public class Television {
    private String model;
    private double diagonal;
    private double price;

    public Television() {
    }

    public Television(String model, double diagonal, double price) {
        this.model = model;
        this.diagonal = diagonal;
        this.price = price;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", diagonal=" + diagonal +
                ", price=" + price +
                '}';
    }

}