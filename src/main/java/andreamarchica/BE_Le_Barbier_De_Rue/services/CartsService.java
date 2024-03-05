package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Cart;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.CartsRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CartsService {
    private final CartsRepository cartsRepository;

    public CartsService(CartsRepository cartRepository) {
        this.cartsRepository = cartRepository;
    }

    public Cart getCartById(UUID cartId) {
        return cartsRepository.findById(cartId).orElseThrow(() -> new NotFoundException(cartId));
    }

    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartsRepository.save(cart);
    }

    public void addItemToCart(UUID cartId, Product product, int quantity) {
        Cart cart = getCartById(cartId);
        cart.addItem(product, quantity);
        // Aggiornare eventualmente altre proprietà come il prezzo totale del carrello
        cartsRepository.save(cart);
    }

    public void removeItemFromCart(UUID cartId, UUID productId) {
        Cart cart = getCartById(cartId);
        cart.removeItem(productId);
        // Aggiornare eventualmente altre proprietà come il prezzo totale del carrello
        cartsRepository.save(cart);
    }

    public double calculateCartTotal(UUID cartId) {
        Cart cart = getCartById(cartId);
        return cart.calculateTotal();
    }
}
