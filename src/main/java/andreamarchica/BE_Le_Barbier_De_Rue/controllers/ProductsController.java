package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.product.NewProductDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.product.NewProductResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    ProductsService productsService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewProductResponseDTO saveProduct(@RequestBody @Validated NewProductDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Product newProduct = productsService.save(body);
        return new NewProductResponseDTO(newProduct.getId());
    }
    @GetMapping("")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String orderBy) {
        return productsService.getProducts(page, size, orderBy);
    }
    @GetMapping("/{productId}")
    public Product findById(@PathVariable UUID productId) {
        return productsService.findById(productId);
    }
    @PutMapping("/{productId}")
    public Product findAndUpdate(@PathVariable UUID productId, @RequestBody NewProductDTO body) {
        return productsService.findByIdAndUpdate(productId, body);
    }
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID productId) {
        productsService.findByIdAndDelete(productId);
    }

}
