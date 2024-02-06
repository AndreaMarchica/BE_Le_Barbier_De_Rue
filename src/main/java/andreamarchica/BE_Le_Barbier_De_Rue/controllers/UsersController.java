package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO saveCliente(@RequestBody @Validated NewUserDTO body, BindingResult validation) throws Exception {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
//        emailSender.sendEmail("andrea.marchica94@gmail.com");
//        emailSender.sendEmail(body.email());
        User newCliente = usersService.save(body);
        return new NewUserResponseDTO(newCliente.getId());
    }
}
