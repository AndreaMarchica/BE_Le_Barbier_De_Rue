package andreamarchica.BE_Le_Barbier_De_Rue.repository;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, UUID> {
}
