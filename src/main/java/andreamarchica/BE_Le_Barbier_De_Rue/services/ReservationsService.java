package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.NewReservationDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationsService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    UsersService usersService;

    public Reservation save(NewReservationDTO body) {
        Reservation reservation = new Reservation();
        User user = usersService.findById(body.userId());
        reservation.setReservationDate(body.reservationDate());
        reservation.setReservedOn(LocalDateTime.now());
        reservation.setAlreadyPayed(false);
        reservation.setHaircutType(body.haircutType());
        reservation.setBeardcutType(body.beardcutType());
        reservation.setUser(user);
    return reservationRepository.save(reservation);
    }
}
