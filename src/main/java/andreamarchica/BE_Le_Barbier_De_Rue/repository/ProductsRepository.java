package andreamarchica.BE_Le_Barbier_De_Rue.repository;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<Product, UUID> {

}
