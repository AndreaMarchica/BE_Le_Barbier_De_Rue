package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Role;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.EmailAlreadyInDbException;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.UnauthorizedException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.auth.AuthRequestDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.user.NewUserResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.auth.TokenResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.UsersRepository;
import andreamarchica.BE_Le_Barbier_De_Rue.security.JWTTtools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;
    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private JWTTtools jwtTtools;

    public TokenResponseDTO authenticateUser(AuthRequestDTO body){
        User user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return new TokenResponseDTO(jwtTtools.createToken(user));
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public NewUserResponseDTO save(NewUserDTO body){
        User user = new User();
        Optional<User> checkEmail= usersRepository.findByEmail(body.email());
        if(checkEmail.isEmpty()){
        user.setName(body.name());
        user.setSurname(body.surname());
        user.setUsername(body.username());
        user.setDateOfBirth(body.dateOfBirth());
        user.setEmail(body.email());
        user.setPassword(bcrypt.encode(body.password()));
        user.setAvatar("https://ui-avatars.com/api/?name=" + body.name() + "+" + body.surname());
        user.setRole(Role.USER);
        usersRepository.save(user);
            return new NewUserResponseDTO(user.getId());
        }else{
            throw new EmailAlreadyInDbException(body.email());
        }
    }
    public User findById(UUID id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public User findByIdAndUpdate(UUID id, NewUserDTO body) {
        User found = this.findById(id);
        found.setName(body.name());
        found.setSurname(body.surname());
        found.setEmail(body.email());
        found.setDateOfBirth(body.dateOfBirth());
        found.setPassword(bcrypt.encode(body.password()));
        found.setUsername(body.username());
        return usersRepository.save(found);
    }

}
