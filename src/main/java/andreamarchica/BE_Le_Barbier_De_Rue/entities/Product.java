package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@ToString
public class Product {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String category;
    private double price;
    private String imageUrl;
    private int discount;
    private int quantity;

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
