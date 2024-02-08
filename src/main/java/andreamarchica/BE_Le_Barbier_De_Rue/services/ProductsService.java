package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.product.NewProductDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductsService {
    @Autowired
    ProductsRepository productsRepository;
    public Product save(NewProductDTO body){
        Product product = new Product();
        product.setName(body.name());
        product.setDescription(body.description());
        product.setCategory(body.category());
        product.setPrice(body.price());
        product.setImageUrl(body.imageUrl());
        product.setStock(body.stock());
        return productsRepository.save(product);
    }
    public Page<Product> getProducts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return productsRepository.findAll(pageable);
    }
    public Product findById(UUID id){
        return productsRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete (UUID id){
        productsRepository.delete(this.findById(id));
    }
    public Product findByIdAndUpdate(UUID id, NewProductDTO body) {
        Product found = this.findById(id);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setCategory(body.category());
        found.setPrice(body.price());
        found.setStock(body.stock());
        found.setDiscount(body.discount());
        return productsRepository.save(found);
    }
}
