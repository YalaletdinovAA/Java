package src.main.test;


import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositories.UsersRepository;
import repositories.impl.UsersRepositoryFileImpl;

import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryFileImplTest {
    private UsersRepository repository;

    @BeforeEach
    void setUp() {
        repository = new UsersRepositoryFileImpl();
    }

    //Тест на то, что пользователь успешно добавляется в репозиторий и может быть найден по его ID.
    @Test
    void createAndFindById() {
        User user = new User(
                "1",
                LocalDateTime.now(),
                "testLogin",
                "testPassword",
                "testPassword",
                "TestLastName",
                "TestFirstName",
                "TestMiddleName",
                30,
                false
        );
        repository.create(user);
        User foundUser = repository.findById("1");
        assertEquals("TestFirstName", foundUser.getFirstName());
    }

    //Тест на то, что данные пользователя успешно обновляются в репозитории
    @Test
    void updateUser() {
        User user = new User(
                "2",
                LocalDateTime.now(),
                "testLogin",
                "testPassword",
                "testPassword",
                "TestLastName",
                "TestFirstName",
                "TestMiddleName",
                30,
                false
        );
        repository.create(user);
        user.setFirstName("UpdatedFirstName");
        repository.update(user);
        User updatedUser = repository.findById("2");
        assertEquals("UpdatedFirstName", updatedUser.getFirstName());
    }

    //Тест на то, что пользователь успешно удаляется из репозитория и больше не может быть найден, выбрасываем исключение.
    @Test
    void deleteById() {
        User user = new User(
                "3",
                LocalDateTime.now(),
                "testLogin",
                "testPassword",
                "testPassword",
                "TestLastName",
                "TestFirstName",
                "TestMiddleName",
                30,
                false
        );
        repository.create(user);
        repository.deleteById("3");
        assertThrows(RuntimeException.class, () -> repository.findById("3"));
    }

    // Тест на удаление пользователя с несуществующим ID
    @Test
    void deleteUserByNonExistentId() {
        // Пытаемся удалить пользователя с несуществующим ID
        assertThrows(RuntimeException.class, () -> repository.deleteById("non-existent-id"));
    }
}