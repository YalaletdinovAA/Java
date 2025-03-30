import cars.Car;
import cars.PerformanceCar;
import cars.ShowCar;
import races.CasualRace;
import repositories.CarRepositoryFile;
import repositories.impl.CarRepositoryFileImpl;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    private static final String DATA_FILE = "src/main/java/homeworks/homework17/src/resources/cars_data.txt";
    private static final String REPOSITORY_FILE = "src/main/java/homeworks/homework17/src/resources/testResources/cars_repository.dat";

    public static void main(String[] args) {
        // Инициализация репозитория
        CarRepositoryFile repository = new CarRepositoryFileImpl(REPOSITORY_FILE);

        // Загрузка данных из файла
        loadInitialData(repository);

        // Создаем автомобили (теперь они берутся из репозитория)
        Car car1 = repository.findById("Lada:Granta:2019").orElseThrow();
        PerformanceCar car2 = (PerformanceCar) repository.findById("Porshe:911:2021").orElseThrow();
        ShowCar car3 = (ShowCar) repository.findById("Chevrole:Camaro:2022").orElseThrow();

        // Создаем гонку
        CasualRace race = new CasualRace(100, "City Circuit", 50000);
        race.addParticipant(car1);
        race.addParticipant(car2);
        race.addParticipant(car3);

        // Выводим информацию
        System.out.println("All cars in repository:");
        repository.findAll().forEach(System.out::println);

        System.out.println("\nRace participants:");
        System.out.println(race);

        // Пример работы с репозиторием
        System.out.println("\nUpdating car...");
        Car updatedCar = new Car("Lada", "Granta", 2019, 180, 9, 9, 120);
        repository.update(updatedCar);
        System.out.println("Updated car: " + repository.findById("Lada:Granta:2019").get());
    }

    private static void loadInitialData(CarRepositoryFile repository) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            for (String line : lines) {
                if (line.startsWith("#") || line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "Car":
                        repository.save(new Car(
                                parts[1], parts[2], Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6]), Integer.parseInt(parts[7])));
                        break;
                    case "PerformanceCar":
                        PerformanceCar pCar = new PerformanceCar(
                                parts[1], parts[2], Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                        repository.save(pCar);
                        break;
                    case "ShowCar":
                        ShowCar sCar = new ShowCar(
                                parts[1], parts[2], Integer.parseInt(parts[3]),
                                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6]), Integer.parseInt(parts[7]));
                        repository.save(sCar);
                        break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading initial data: " + e.getMessage());
        }
    }
}