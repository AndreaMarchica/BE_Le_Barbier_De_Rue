package andreamarchica.BE_Le_Barbier_De_Rue.payloads;

public record AuthRequestDTO(
        String email,
        String password
) {
}
