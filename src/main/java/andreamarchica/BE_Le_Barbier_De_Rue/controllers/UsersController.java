package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Service;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.service.NewServiceDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.UsersRepository;
import andreamarchica.BE_Le_Barbier_De_Rue.services.AuthService;
import andreamarchica.BE_Le_Barbier_De_Rue.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @Autowired
    AuthService authService;
    @Autowired
    UsersRepository usersRepository;

    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int size,
                              @RequestParam(defaultValue = "id") String orderBy) {
        return usersService.getUsers(page, size, orderBy);
    }
    @GetMapping("/{userId}")
    public User findById(@PathVariable UUID userId) {
        return usersService.findById(userId);
    }
    @PutMapping("/{userId}")
    public User findAndUpdate(@PathVariable UUID userId, @RequestBody NewUserDTO body) {
        return authService.findByIdAndUpdate(userId, body);
    }
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Optional<User> findById(@AuthenticationPrincipal User currentUser){
        return usersRepository.findById(currentUser.getId());
    }
}
