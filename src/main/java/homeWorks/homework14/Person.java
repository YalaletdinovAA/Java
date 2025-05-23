package homeworks.homework14;

import java.util.ArrayList;
import java.util.List;

/*
Формулировка задания:
1.Создать классы Покупатель (Person) и Продукт (Product).
Характеристики Покупателя: имя, сумма денег и  пакет с продуктами(массив объектов типа Продукт).
 Имя не может быть пустой строкой. Деньги немогут быть отрицательным числом.
 Если Покупатель может позволить себе Продукт, то Продукт добавляетсяв пакет.
 Если у Покупателя недостаточно денег, то добавление не происходит.Характеристики Продукта: название и стоимость.
  Название продукта неможет быть пустой строкой, оно должно быть.
  Стоимость продукта не можетбыть отрицательным числом.
  2. Поля в классах должны быть private,
   доступ к полям осуществляетсячерез геттеры и сеттеры или конструктор класса.
   3. В классах переопределены методы toString(), equals(), hashcode().
   4.  Создать в классе  App  метод  main  и проверить работу приложения.
   Данные   Покупателей   и   Продукты   вводятся   с   клавиатуры   или   задаютсяслучайным образом.
   Продукты в цикле выбираются покупателями по очередии, пока не введено слово END, наполняется пакет.
   5. Обработать следующие ситуации:а.   Если   покупатель   не   может   позволить   себе   продукт,
 тонапечатайте соответствующее сообщение ("[Имя   человека] не может позволить себе [Название продукта]").
 б. Если ничего не куплено, выведите имя человека, за которымследует "Ничего не куплено".
 в. В случае неверного ввода (сообщение об исключении: "Деньги немогут   быть   отрицательными")   или
  пустого   имени:   (сообщение   обисключении: "Имя не может быть пустым").
  6. Программа реализуется в отдельной ветке git homeworks/homework06.При   сохранении   состояния   программы   (коммиты)
  пишется   сообщение   с описанием хода работы по задаче. В корне папки с программой должен быть файл
   .gitignore.Программа локально коммитится и публикуется в репозиторий GitHub напроверку.
   Тестовые данные:

 Павел Андреевич = 10000; Анна Петровна = 2000, Борис = 10
 Хлеб = 40; Молоко = 60; Торт = 1000; Кофе растворимый = 879; Масло = 150;

 Павел Андреевич Хлеб
 Павел Андреевич  Масло
 Анна Петровна  Кофе растворимый
 Анна Петровна   Молоко
 Анна Петровна   Молоко
 Анна Петровна   Молоко
 Анна Петровна  Торт
 Борис Торт
 Павел Андреевич  Торт
 END

 Женя = 0;
 Мороженое = 200
 Женя Мороженое
 END

 Света = -3
 Макароны = 800;
 Света Макароны
 END

Ожидаемый результат:

Павел Андреевич купил Хлеб
 Павел Андреевич  купил Масло
 Анна Петровна  купила Кофе
 растворимый
 Анна Петровна  купила Молоко
 Анна Петровна  купила Молоко
 Анна Петровна  купила Молоко
 Анна Петровна  не может позволить себе
 Торт
 Борис  не может позволить себе Торт
 Павел Андреевич купил Торт
 Павел Андреевич - Хлеб, Масло, Торт
 Анна Петровна - Кофе растворимый,
 Молоко, Молоко, Молоко
 Борис -  Ничего не куплено

 Женя не может позволить себе
 Мороженое
 Женя -  Ничего не куплено

 Деньги не могут быть отрицательными
*/
public class Person {
    private String name;
    private double money;
    private List<Product> basket;

    public Person() {
    }

    public Person(String name, double money) {
        if (name == null || name.isEmpty()) {
            System.out.println("Имя не может быть пустым");
            this.name = "";
        } else {
            this.name = name;
        }

        if (money < 0) {
            System.out.println("Деньги не могут быть отрицательными");
            this.money = 0;
        } else {
            this.money = money;
        }

        this.basket = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setBasket(List<Product> basket) {
        this.basket = basket;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public List<Product> getBasket() {
        return basket;
    }

    // Метод для покупки продукта
    public void buyProduct(Product product) {
        if (product != null && money >= product.getPrice()) {
            basket.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else if (product != null) {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", basket=" + basket +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return Double.compare(person.money, money) == 0 && name.equals(person.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + Double.hashCode(money);
    }
}
