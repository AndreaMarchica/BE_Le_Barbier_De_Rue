package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Reservation {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDateTime reservationDate;
    private LocalDateTime reservedOn;
    private boolean alreadyPayed;
    @Enumerated(EnumType.STRING)
    private HaircutType haircutType;
    @Enumerated(EnumType.STRING)
    private BeardcutType beardcutType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public void setReservedOn(LocalDateTime reservedOn) {
        this.reservedOn = reservedOn;
    }

    public void setAlreadyPayed(boolean alreadyPayed) {
        this.alreadyPayed = alreadyPayed;
    }

    public void setHaircutType(HaircutType haircutType) {
        this.haircutType = haircutType;
    }

    public void setBeardcutType(BeardcutType beardcutType) {
        this.beardcutType = beardcutType;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
