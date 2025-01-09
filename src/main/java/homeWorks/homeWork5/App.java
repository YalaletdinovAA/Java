package src.main.java.homeWorks.homeWork5;

import java.util.*;

public class App {
    public static void main(String[] args) {
       /*
       Television tv = new Television();
        Television tv1 = new Television();
        //Параметры через конструктор
        Television tv2 = new Television("Samsung", 43.5, 30000);
        Television tv3 = new Television("Philips", 50.0, 45000);

        //Задаем через set параметры для tv и tv1
        tv.setDiagonal(55.0);
        tv.setModel("Siemens");
        tv.setPrice(50000);

        tv1.setDiagonal(40.0);
        tv1.setModel("Lg");
        tv1.setPrice(5400);

        //Вывод:
        System.out.println(tv);
        System.out.println(tv1);
        System.out.println(tv2);
        System.out.println(tv3);
*/
        //Ввод с консоли:
        Scanner scanner = new Scanner(System.in);
/*
        System.out.println("Введите марку телевизора: ");
        String model = scanner.nextLine();

        System.out.println("Введите размер экрана (в дюймах): ");
        double diagonal = scanner.nextDouble();

        System.out.println("Введите стоимость: ");
        double price = scanner.nextDouble();


        Television tvPerson = new Television();
        tvPerson.setModel(model);
        tvPerson.setDiagonal(diagonal);
        tvPerson.setPrice(price);

        System.out.println(tvPerson);


        //Ввод через Рандом
        Television tvRand = new Television();
        tvRand.setModel("Fusion");
        Random random = new Random();
        tvRand.setDiagonal(random.nextInt(32, 60));
        tvRand.setPrice(random.nextInt(30000, 45000));
        System.out.println(tvRand);
*/
        // Считываем maxVolume
        System.out.print("Введите громкость от 50-70: ");
        int maxVolume = scanner.nextInt();
        if (maxVolume < 50 || maxVolume > 70) {
            System.out.println("Неверное значение. Громкость должна быть от 50 до 70.");

        } else {
            // Создаем массив телевизоров
            List<Television> televisions = new ArrayList<>();

            for (int i = 1; i <= 10; i++) {
                System.out.print("Введите модель телевизора номер - " + i + ": ");
                String modelTv = scanner.next();

                System.out.print("Введите номер канала телевизора - " + i + ": ");
                int channelNumber = scanner.nextInt();

                System.out.print("Введите громкость телевизора - " + i + ": ");
                int volume = scanner.nextInt();

                System.out.print(" Включить телевизор " + i + " ? (true/false): ");
                boolean isOn = scanner.nextBoolean();

                televisions.add(new Television(modelTv, channelNumber, volume, isOn));
            }

            // Вывод включенных телевизоров с допустимой громкостью
            System.out.println("Включенные телевизоры с громкостью <= " + maxVolume + ":");
            for (Television tv : televisions) {
                if (tv.isOn() && tv.getVolume() <= maxVolume) {
                    System.out.println(tv);
                }
            }
            // Сортировка телевизоров по номеру канала
            televisions.sort(Comparator.comparing(Television::getChannelNumber));


        }

    }

}


