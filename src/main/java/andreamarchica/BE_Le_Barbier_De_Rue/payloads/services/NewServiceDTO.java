package andreamarchica.BE_Le_Barbier_De_Rue.payloads.services;

public record NewServiceDTO(
        String name,
        String description,
        String category,
        double price,
        int discount
) {
}
