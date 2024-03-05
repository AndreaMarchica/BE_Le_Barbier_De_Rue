package andreamarchica.BE_Le_Barbier_De_Rue.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class CartItem {
    private Product product;
    private int quantity;

}
