package andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.BeardcutType;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.HaircutType;

import java.time.LocalDateTime;
import java.util.UUID;

public record NewReservationDTO(
        LocalDateTime reservationDate,
        HaircutType haircutType,
        BeardcutType beardcutType,
        UUID userId
) {
}
