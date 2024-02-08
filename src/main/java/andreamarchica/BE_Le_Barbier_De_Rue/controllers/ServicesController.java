package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Service;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.product.NewProductDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.service.NewServiceDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.service.NewServiceResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewServiceResponseDTO saveService(@RequestBody @Validated NewServiceDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        Service newService = servicesService.save(body);
        return new NewServiceResponseDTO(newService.getId());
    }
    @GetMapping("")
    public Page<Service> getServices(
            @RequestParam(defaultValue ="0") int page,
            @RequestParam(defaultValue ="0") int size,
            @RequestParam(defaultValue ="0") String sortBy) {
        return servicesService.getServices(page, size, sortBy);
    }
    @GetMapping("/{serviceId}")
    public Service findById(@PathVariable UUID serviceId) {
        return servicesService.findById(serviceId);
    }
    @PutMapping("/{serviceId}")
    public Service findAndUpdate(@PathVariable UUID serviceId, @RequestBody NewServiceDTO body) {
        return servicesService.findByIdAndUpdate(serviceId, body);
    }
    @DeleteMapping("/{serviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID serviceId) {
        servicesService.findByIdAndDelete(serviceId);
    }

}
