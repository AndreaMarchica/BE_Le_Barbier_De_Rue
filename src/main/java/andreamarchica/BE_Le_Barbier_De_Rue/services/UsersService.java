package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    public User save(NewUserDTO body){
        User user = new User();
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setDateOfBirth(body.dateOfBirth());
        user.setEmail(body.email());
        user.setPassword(body.password());
        user.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        return usersRepository.save(user);
    }
    public User findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    public User findByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }
}
