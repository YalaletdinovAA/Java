package src.main.java.homeWorks.homeWork5;

import java.util.Objects;

/*
 Дополнительная задача:
 Доработать класс Телевизор:
 1. В класс Телевизор добавить поля (если не были добавлены ранее):
 1) Номер включенного канала — integer;
 2) Громкость звука — integer (от 0 до 100);
 3) Признак включенного телевизора — boolean.
 2. Переопределить метод toString класса Телевизор таким образом, чтобы
 распечатывались: название класса, все поля класса и их значения.
 3. Добавить в класс Телевизор методы equals и hashcode.
На вход программы в классе App, методе main подается информация о
 телевизорах в количестве 10 телевизоров. Считать данные в массив объектов
 (можно выполнить считывание данных в цикле). В каждом из 10 экземпляров
 класса должны быть заполнены следующие поля:
 a. Как минимум, 1 поле по выбору, добавленное студентом в задании 3;
b. Номер включенного канала - целое число,
c. Громкость звука - целое число;
 d. Признак включен ли телевизор.
 Считать с клавиатуры число допустимого значения громкости звука
 maxVolume (рекомендуется ввести с клавиатуры целое число от 50 до 70).
 Далее необходимо в цикле вывести только включенные телевизоры, у
 которых звук является допустимым (меньшим или равным maxVolume).
Дополнительно. Добавить в массив Телевизоров сортировку по номеру
 канала (по возрастанию).
 */
public class Television {
    private String model;
    private double diagonal;
    private double price;

    private int channelNumber;
    private int volume;
    private boolean isOn;


    public Television() {
    }

    public Television(String model, double diagonal, double price) {
        this.model = model;
        this.diagonal = diagonal;
        this.price = price;
    }

    public Television(String model, boolean isOn, int channelNumber, double diagonal, double price, int volume) {
        this.model = model;
        this.isOn = isOn;
        this.channelNumber = channelNumber;
        this.diagonal = diagonal;
        this.price = price;
        this.volume = volume;
    }

    public Television(int channelNumber, int volume, boolean isOn) {
        this.channelNumber = channelNumber;
        this.volume = volume;
        this.isOn = isOn;
    }

    public Television(String model, int channelNumber, int volume, boolean isOn) {
        this.model = model;
        this.channelNumber = channelNumber;
        this.volume = volume;
        this.isOn = isOn;
    }


    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public int getChannelNumber() {
        return channelNumber;
    }

    public void setChannelNumber(int channelNumber) {
        this.channelNumber = channelNumber;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Television that = (Television) o;
        return Double.compare(diagonal, that.diagonal) == 0 && Double.compare(price, that.price) == 0 && channelNumber == that.channelNumber && volume == that.volume && isOn == that.isOn && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, diagonal, price, channelNumber, volume, isOn);
    }

    @Override
    public String toString() {
        return "Television{" +
                "model='" + model + '\'' +
                ", diagonal=" + diagonal +
                ", price=" + price +
                ", channelNumber=" + channelNumber +
                ", volume=" + volume +
                ", isOn=" + isOn +
                '}';
    }

}