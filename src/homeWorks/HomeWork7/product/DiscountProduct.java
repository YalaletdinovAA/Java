package src.homeWorks.HomeWork7.product;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountProduct extends Product {

    private Integer discaunt;
    private LocalDate discauntDate;
    private Boolean isDiscounted;

    public DiscountProduct(Integer price, String name, Integer discaunt, LocalDate discauntDate, Boolean isDiscounted) {
        super(price, name);
        this.discaunt = discaunt;
        this.discauntDate = discauntDate;
        this.isDiscounted = isDiscounted;
    }

    public Integer getDiscaunt() {
        return discaunt;
    }

    public void setDiscaunt(Integer discaunt) {
        this.discaunt = discaunt;
    }

    public LocalDate getDiscauntDate() {
        return discauntDate;
    }

    public void setDiscauntDate(LocalDate discauntDate) {
        this.discauntDate = discauntDate;
    }

    public Boolean getDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Objects.equals(discaunt, that.discaunt) && Objects.equals(discauntDate, that.discauntDate) && Objects.equals(isDiscounted, that.isDiscounted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discaunt, discauntDate, isDiscounted);
    }

    @Override
    public String toString() {
        return "DiscountProduct{" +
                "name="+ super.getName()+
                ", price="+ super.getPrice()+
                ", discaunt=" + discaunt +
                ", discauntDate=" + discauntDate +
                ", isDiscounted=" + isDiscounted +
                '}';
    }
}
