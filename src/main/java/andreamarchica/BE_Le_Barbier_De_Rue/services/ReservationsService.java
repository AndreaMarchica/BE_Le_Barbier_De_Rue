package andreamarchica.BE_Le_Barbier_De_Rue.services;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.User;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.NotFoundException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation.NewReservationDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation.NewReservationResponseDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation.ReservationUpdateDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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
        reservation.setHaircutId(body.haircutId());
        reservation.setBeardcutId(body.beardcutId());
        reservation.setComboId(body.comboId());
        reservation.setUser(user);
    return reservationsRepository.save(reservation);
    }
/*    public Page<Reservation> getReservations(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return reservationsRepository.findAll(pageable);
    }*/
    public List<Reservation> getReservations() {
        return reservationsRepository.findAll();
    }
    public Reservation findById(UUID id){
        return reservationsRepository.findById(id).orElseThrow(()->new NotFoundException(id));
    }

    public void findByIdAndDelete (UUID id){
        reservationsRepository.delete(this.findById(id));
    }
    public NewReservationResponseDTO modify(ReservationUpdateDTO body, UUID id){
        Optional<Reservation> optionalReservation = reservationsRepository.findById(id);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (body.haircutId() != null) {
                reservation.setHaircutId(body.haircutId());
            }
            if (body.beardcutId() != null) {
                reservation.setBeardcutId(body.beardcutId());
            }
            if (body.comboId() != null) {
                reservation.setComboId(body.comboId());
            }
            reservationsRepository.save(reservation);
            return new NewReservationResponseDTO(id);
        }   else throw new NotFoundException("Reservation not found with id: " + id);
    }


}
