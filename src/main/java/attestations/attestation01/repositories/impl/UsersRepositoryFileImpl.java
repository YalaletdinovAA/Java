package src.main.java.attestations.attestation01.repositories.impl;


import src.main.java.attestations.attestation01.model.User;
import src.main.java.attestations.attestation01.repositories.UsersRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryFileImpl implements UsersRepository {
    private static final List<User>USERS = new ArrayList<>();
    private static final String FILE_NAME = "src/main/resources/users.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Override
    public void create(User user) {
        List<User> users = findAll();
        users.add(user);
        writeToFile(users);
    }

    @Override
    public User findById(String id) {
        List<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        throw new RuntimeException("Пользователя с заданным идентификатором не существует");
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                User user = new User(
                        parts[0],
                        LocalDateTime.parse(parts[1], DATE_TIME_FORMATTER),
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        parts[6],
                        parts[7],
                        Integer.parseInt(parts[8]),
                        Boolean.parseBoolean(parts[9])
                );
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return USERS;
    }

    @Override
    public void update(User user) {
        List<User> users = findAll();
        boolean found = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                found = true;
                break;
            }
        }
        if (!found) {
            users.add(user);
        }
        writeToFile(users);
    }

    @Override
    public void deleteById(String id) {
        List<User> users = findAll();
        boolean removed = users.removeIf(user -> user.getId().equals(id));
        if (!removed) {
            throw new RuntimeException("Пользователя с заданным идентификатором не существует");
        }
        writeToFile(users);
    }

    @Override
    public void deleteAll() {
        writeToFile(new ArrayList<>());
    }

    private void writeToFile(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : users) {
                writer.write(user.getId() + "|" +
                        user.getRegistrationDate().format(DATE_TIME_FORMATTER) + "|" +
                        user.getLogin() + "|" +
                        user.getPassword() + "|" +
                        user.getConfirmPassword() + "|" +
                        user.getLastName() + "|" +
                        user.getFirstName() + "|" +
                        user.getMiddleName() + "|" +
                        user.getAge() + "|" +
                        user.isWorker());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}