package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.products.NewProductDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.products.NewProductResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
