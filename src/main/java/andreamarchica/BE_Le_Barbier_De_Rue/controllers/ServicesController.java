package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Service;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.services.NewServiceDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.services.NewServiceResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
