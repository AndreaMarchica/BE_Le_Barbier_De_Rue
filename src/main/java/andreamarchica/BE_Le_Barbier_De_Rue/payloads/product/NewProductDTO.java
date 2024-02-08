package andreamarchica.BE_Le_Barbier_De_Rue.payloads.product;

public record NewProductDTO(
        String name,
        String description,
        String category,
        double price,
        String imageUrl,
        int stock,
        int discount
) {
}
