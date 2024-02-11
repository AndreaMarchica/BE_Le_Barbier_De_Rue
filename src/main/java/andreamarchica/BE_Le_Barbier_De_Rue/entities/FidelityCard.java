package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
public class FidelityCard {
    @Id
    @GeneratedValue
    private UUID id;
    private boolean complete;
    private int points;
    @OneToOne
    private User user;

    public FidelityCard() {
        this.points = 0;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addPoint() {
        points++;
        if (points >= 10) {
            setComplete(true);
        }
    }

    @Override
    public String toString() {
        return "FidelityCard{" +
                "id=" + id +
                ", complete=" + complete +
                ", points=" + points +
                '}';
    }
    // Altri attributi e metodi necessari...

    // Getter e Setter per isComplete, se necessario
}
