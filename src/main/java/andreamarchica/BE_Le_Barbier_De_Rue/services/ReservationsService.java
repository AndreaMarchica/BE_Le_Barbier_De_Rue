package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation.NewReservationDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ReservationsService {
    @Autowired
    ReservationsRepository reservationsRepository;
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
    return reservationsRepository.save(reservation);
    }
    public Page<Reservation> getReservations(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return reservationsRepository.findAll(pageable);
    }
    public Reservation findById(UUID id){
        return reservationsRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }
    public void findByIdAndDelete (UUID id){
        reservationsRepository.delete(this.findById(id));
    }


}
