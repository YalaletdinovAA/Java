package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class User {
    private String id;
    private LocalDateTime registrationDate;
    private String login;
    private String password;
    private String confirmPassword;
    private String lastName;
    private String firstName;
    private String middleName;
    private Integer age;
    private boolean isWorker;

    public User() {
    }

    public User(String id, LocalDateTime registrationDate, String login, String password, String confirmPassword,
                String lastName, String firstName, String middleName, Integer age, boolean isWorker) {
        this.id = id;
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.isWorker = isWorker;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isWorker() {
        return isWorker;
    }

    public void setWorker(boolean worker) {
        isWorker = worker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isWorker == user.isWorker && Objects.equals(id, user.id) && Objects.equals(registrationDate, user.registrationDate) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(confirmPassword, user.confirmPassword) && Objects.equals(lastName, user.lastName) && Objects.equals(firstName, user.firstName) && Objects.equals(middleName, user.middleName) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationDate, login, password, confirmPassword, lastName, firstName, middleName, age, isWorker);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", registrationDate=" + registrationDate +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", age=" + age +
                ", isWorker=" + isWorker +
                '}';
    }
}
