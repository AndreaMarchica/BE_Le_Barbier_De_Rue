package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.auth.AuthRequestDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.auth.TokenResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO register(@RequestBody @Validated NewUserDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            System.err.println(bindingResult.getAllErrors());
            throw new BadRequestException("errore nell' invio del payload per il metodo POST" + bindingResult.getAllErrors());
        } else {
        return authService.save(body);
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDTO generateToken(@RequestBody AuthRequestDTO body){
        return authService.authenticateUser(body);
    }

}
