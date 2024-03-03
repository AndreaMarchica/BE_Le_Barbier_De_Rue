package andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation;

import java.util.UUID;

public record ReservationUpdateDTO(
        UUID haircutId,
        UUID beardcutId,
        UUID comboId
) {
}
