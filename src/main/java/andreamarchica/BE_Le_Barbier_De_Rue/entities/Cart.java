package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Cart {
    @Id
    @GeneratedValue
    private UUID id;
    private int discount;
    private double totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    List<Product> productList;
    @OneToOne
    private User user;
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

