package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.NewProductDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    @Autowired
    ProductRepository productRepository;
    public Product save(NewProductDTO body){
        Product product = new Product();
        product.setName(body.name());
        product.setDescription(body.description());
        product.setCategory(body.category());
        product.setPrice(body.price());
        product.setImageUrl(body.imageUrl());
        product.setQuantity(body.quantity());
        return productRepository.save(product);
    }
}
