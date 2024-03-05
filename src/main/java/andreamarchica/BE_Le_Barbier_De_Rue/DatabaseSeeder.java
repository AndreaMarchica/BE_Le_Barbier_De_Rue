package andreamarchica.BE_Le_Barbier_De_Rue;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Role;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usersRepository.count() == 0) {
            populateDatabase();
        }
    }

    private void populateDatabase() {
        User user1 = new User();
        user1.setUsername("Samu94");
        user1.setName("Giacinto");
        user1.setSurname("D'Agnano'");
        user1.setDateOfBirth(LocalDate.of(1994, 3, 2));
        user1.setEmail("samueledagnano@gmail.com");
        user1.setPassword("1234");
        user1.setPhoneNumber(320145696);
        user1.setAvatar("https://ui-avatars.com/api/?name=Giacinto+Dagnano");
        user1.setRole(Role.ADMIN);

        User user2 = new User();
        user2.setUsername("jane_doe");
        user2.setName("Jane");
        user2.setSurname("Doe");
        user2.setDateOfBirth(LocalDate.of(1985, 8, 25));
        user2.setEmail("jane.doe@example.com");
        user2.setPassword("password456");
        user2.setPhoneNumber(987654321);
        user2.setAvatar("https://example.com/avatar/jane_doe.jpg");
        user2.setRole(Role.USER);

        usersRepository.saveAll(List.of(user1, user2));
    }
}
