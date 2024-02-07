package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.payloads.services.NewServiceDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public andreamarchica.BE_Le_Barbier_De_Rue.entities.Service save(NewServiceDTO body){
        andreamarchica.BE_Le_Barbier_De_Rue.entities.Service service = new andreamarchica.BE_Le_Barbier_De_Rue.entities.Service();
        service.setName(body.name());
        service.setDescription(body.description());
        service.setCategory(body.category());
        service.setPrice(body.price());
        service.setDiscount(body.discount());
        return servicesRepository.save(service);
    }
}
