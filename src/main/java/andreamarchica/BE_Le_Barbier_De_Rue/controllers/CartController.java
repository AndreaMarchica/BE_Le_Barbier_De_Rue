package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Cart;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.CartItem;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ProductsService;
import andreamarchica.BE_Le_Barbier_De_Rue.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    UsersService usersService;
    @Autowired
    ProductsService productsService;
    private Map<UUID, Cart> userCarts = new HashMap<>();

    @PostMapping("/add")
    public void addToCart(@RequestParam UUID userId, @RequestParam UUID productId, @RequestParam int quantity) {
        User user = usersService.findById(userId);

        if (!userCarts.containsKey(userId)) {
            userCarts.put(userId, new Cart(user));
        }

        Cart cart = userCarts.get(userId);
        Product product = productsService.findById(productId);
        cart.addItem(product, quantity);
    }

    @PostMapping("/remove")
    public void removeFromCart(@RequestParam UUID userId, @RequestParam UUID productId) {
        if (userCarts.containsKey(userId)) {
            Cart cart = userCarts.get(userId);
            cart.removeItem(productId);
        }
    }

    @GetMapping("/items")
    public List<CartItem> getCartItems(@RequestParam UUID userId) {
        return userCarts.getOrDefault(userId, new Cart()).getItems();
    }

    @GetMapping("/total")
    public double getCartTotal(@RequestParam UUID userId) {
        return userCarts.getOrDefault(userId, new Cart()).calculateTotal();
    }
}