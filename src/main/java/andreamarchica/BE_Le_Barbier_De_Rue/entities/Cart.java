package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
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
    private List<CartItem> items = new ArrayList<>();
    @OneToOne
    private User user;
/*    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;*/
public Cart() {

}

    public Cart(User user) {
        this.user = user;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    public void addItem(Product product, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId().equals(product.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        CartItem newItem = new CartItem();
        newItem.setProduct(product);
        newItem.setQuantity(quantity);
        items.add(newItem);
        calculateTotal();
    }

    public void removeItem(UUID productId) {
        items.removeIf(item -> item.getProduct().getId().equals(productId));
        calculateTotal();
    }

    public double calculateTotal() {
        double total = items.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        this.totalPrice = total;
        return total;
    }
/*    public void setPayment(Payment payment) {
        this.payment = payment;
    }*/

}

