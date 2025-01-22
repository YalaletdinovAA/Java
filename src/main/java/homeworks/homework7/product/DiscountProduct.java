package src.main.java.homeworks.homework7.product;

import java.time.LocalDate;
import java.util.Objects;


public class DiscountProduct extends Product {
    private double discount;  // Процент скидки
    private LocalDate discountExpiryDate;  // Срок действия скидки

    public DiscountProduct(String name, double price, double discount, String discountExpiryDate) {
        super(name, price);


        if (discount <= 0 || discount >= 100) {
            this.discount = 0; // Если скидка неверна, применяем нулевую скидку
        } else {
            this.discount = discount;
        }

        // Срок действия скидки
        LocalDate expiryDate = LocalDate.parse(discountExpiryDate);
        if (expiryDate.isBefore(LocalDate.now())) {
            this.discountExpiryDate = LocalDate.now(); // Устанавливаем текущую дату как срок действия скидки
        } else {
            this.discountExpiryDate = expiryDate;
        }
    }

    @Override
    public double getPrice() {
        // Если скидка еще актуальна, применяется скидка
        if (LocalDate.now().isBefore(discountExpiryDate)) {
            return super.getPrice() * (1 - discount / 100);
        } else {
            return super.getPrice();  // Если скидка прошла, показываем обычную цену
        }
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getDiscountExpiryDate() {
        return discountExpiryDate;
    }

    public void setDiscountExpiryDate(LocalDate discountExpiryDate) {
        this.discountExpiryDate = discountExpiryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Double.compare(discount, that.discount) == 0 && Objects.equals(discountExpiryDate, that.discountExpiryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, discountExpiryDate);
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                "discount=" + discount +
                ", discountExpiryDate=" + discountExpiryDate +
                '}';
    }

}