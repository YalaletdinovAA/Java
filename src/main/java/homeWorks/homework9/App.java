package src.main.java.homeworks.homework9;
public class App {
    public static void main(String[] args) {
        // Создаем автомобили
        src.main.java.homeworks.homework9.Car car1 = new src.main.java.homeworks.homework9.Car("Toyota", "Corolla", 2019, 150, 10, 8, 100);
        src.main.java.homeworks.homework9.PerformanceCar car2 = new src.main.java.homeworks.homework9.PerformanceCar("Ferrari", "488", 2021, 700, 3, 10, 200);
        src.main.java.homeworks.homework9.ShowCar car3 = new src.main.java.homeworks.homework9.ShowCar("Lamborghini", "Aventador", 2022, 800, 2, 12, 300);

        // Добавляем дополнения для PerformanceCar
        car2.addAddOn("Turbo Boost");
        car2.addAddOn("Nitrous Oxide");

        // Устанавливаем звезды для ShowCar
        car3.setStars(5);

        // Создаем гонку
        src.main.java.homeworks.homework9.CasualRace race = new src.main.java.homeworks.homework9.CasualRace(100, "City Circuit", 50000);
        race.addParticipant(car1);
        race.addParticipant(car2);
        race.addParticipant(car3);

        // Создаем гараж
        src.main.java.homeworks.homework9.Garage garage = new src.main.java.homeworks.homework9.Garage();
        garage.parkCar(car1);
        garage.parkCar(car2);
        garage.parkCar(car3);

        // Выводим информацию
        System.out.println(car1);
        System.out.println(car2);
        System.out.println(car3);
        System.out.println(race);
        System.out.println(garage);
    }
}
