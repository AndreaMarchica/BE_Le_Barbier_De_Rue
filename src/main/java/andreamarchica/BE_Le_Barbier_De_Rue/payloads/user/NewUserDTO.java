package andreamarchica.BE_Le_Barbier_De_Rue.payloads.user;

import java.time.LocalDate;

public record NewUserDTO(
        String name,
        String surname,
        String username,
        LocalDate dateOfBirth,
        String email,
        String password
) {
}
