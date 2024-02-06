package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@ToString
public class Payment {
    @Id
    @GeneratedValue
    private UUID id;
    private double amount;
    @OneToOne
    private Cart cart;
    // Altri attributi e metodi necessari...

    // Logic to associate Payment with FidelityCard and update points


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void processPayment(FidelityCard fidelityCard) {
        // Logica del pagamento...

        // Aggiorna la fidelity card
        fidelityCard.addPoint();
    }
}
