package src.main.java.homeworks.homework012;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в произвольном порядке, разделенные пробелом:");
        System.out.println("Фамилия Имя Отчество дата рождения (dd.mm.yyyy) номер телефона пол(f/m) возраст");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        if (data.length != 7) {
            System.err.println("Ошибка: количество данных не совпадает с требуемым.");
            return;
        }

        src.main.java.homeworks.homework012.Person person = new src.main.java.homeworks.homework012.Person();
        try {
            parseData(data, person);
            saveToFile(person);
            System.out.println("Данные успешно записаны в файл.");
        } catch (src.main.java.homeworks.homework012.PersonDataException | IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }

    private static void parseData(String[] data, src.main.java.homeworks.homework012.Person person) throws src.main.java.homeworks.homework012.PersonDataException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);

        for (String item : data) {
            try {
                // Проверка на дату
                Date date = dateFormat.parse(item);
                person.setBirthDate(item);
                continue;
            } catch (ParseException e) {
                // Не дата, продолжаем проверку
            }

            if (item.length() == 1 && (item.charAt(0) == 'f' || item.charAt(0) == 'm')) {
                // Проверка на пол
                person.setGender(item.charAt(0));
                continue;
            }

            try {
                // Проверка на возраст (предполагаем, что возраст — это число от 1 до 120)
                int age = Integer.parseInt(item);
                if (age >= 1 && age <= 120) {
                    person.setAge(age);
                    continue;
                }
            } catch (NumberFormatException e) {
                // Не возраст, продолжаем проверку
            }

            try {
                // Проверка на номер телефона (предполагаем, что номер телефона — это число из 10–12 цифр)
                long phoneNumber = Long.parseLong(item);
                if (item.length() >= 10 && item.length() <= 12) {
                    person.setPhoneNumber(phoneNumber);
                    continue;
                }
            } catch (NumberFormatException e) {
                // Не номер телефона, продолжаем проверку
            }

            // Если не удалось определить тип данных, предполагаем, что это ФИО
            if (person.getLastName() == null) {
                person.setLastName(item);
            } else if (person.getFirstName() == null) {
                person.setFirstName(item);
            } else if (person.getPatronymic() == null) {
                person.setPatronymic(item);
            } else {
                throw new src.main.java.homeworks.homework012.PersonDataException("Неверный формат данных: " + item);
            }
        }
    }

    private static void saveToFile(src.main.java.homeworks.homework012.Person person) throws IOException {
        String fileName = person.getLastName() + ".txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(person.toString() + "\n");
        }
    }
}
