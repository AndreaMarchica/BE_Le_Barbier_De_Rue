package andreamarchica.BE_Le_Barbier_De_Rue.payloads.auth;

public record AuthRequestDTO(
        String email,
        String password
) {
}
