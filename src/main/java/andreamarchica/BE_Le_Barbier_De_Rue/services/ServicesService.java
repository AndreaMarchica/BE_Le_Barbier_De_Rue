package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.service.NewServiceDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
/*    public Page<andreamarchica.BE_Le_Barbier_De_Rue.entities.Service> getServices(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return servicesRepository.findAll(pageable);
    }*/
    public List<andreamarchica.BE_Le_Barbier_De_Rue.entities.Service> getServices() {
        return servicesRepository.findAll(); // Supponendo che tu abbia un repository chiamato servicesRepository
    }
    public andreamarchica.BE_Le_Barbier_De_Rue.entities.Service findById(UUID id){
        return servicesRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete (UUID id){
        servicesRepository.delete(this.findById(id));
    }
    public andreamarchica.BE_Le_Barbier_De_Rue.entities.Service findByIdAndUpdate(UUID id, NewServiceDTO body) {
        andreamarchica.BE_Le_Barbier_De_Rue.entities.Service found = this.findById(id);
        found.setName(body.name());
        found.setDescription(body.description());
        found.setCategory(body.category());
        found.setPrice(body.price());
        found.setDiscount(body.discount());
        return servicesRepository.save(found);
    }
}


