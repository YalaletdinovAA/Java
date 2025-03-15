

import model.User;
import repositories.UsersRepository;
import repositories.impl.UsersRepositoryFileImpl;

import java.time.LocalDateTime;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        UsersRepository repository = new UsersRepositoryFileImpl();

        // Создание пользователя
        User user = new User(
                UUID.randomUUID().toString(),
                LocalDateTime.now(),
                "noisemc_99",
                "789ghs",
                "789ghs",
                "Крылов",
                "Виктор",
                "Павлович",
                25,
                true
        );
        repository.create(user);

        // Поиск пользователя по ID
        User foundUser = repository.findById(user.getId());
        System.out.println("Найден пользователь: " + foundUser.getFirstName());

        // Выгрузка всех пользователей
        System.out.println("Все пользователи:");
        repository.findAll().forEach(u -> System.out.println(u.getFirstName()));

        // Обновление пользователя
        user.setFirstName("Иван");
        repository.update(user);

        // Удаление пользователя по ID
        repository.deleteById(user.getId());

        // Удаление всех пользователей
        repository.deleteAll();
    }
}