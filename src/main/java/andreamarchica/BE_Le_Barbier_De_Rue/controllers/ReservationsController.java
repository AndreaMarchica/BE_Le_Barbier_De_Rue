package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Product;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import andreamarchica.BE_Le_Barbier_De_Rue.entities.Service;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.reservation.NewReservationDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    @Autowired
    private ReservationsService reservationsService;
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveReservation(@RequestBody @Validated NewReservationDTO body, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new BadRequestException("errore nel payload!" + bindingResult.getAllErrors());
        } else {
            return reservationsService.save(body);
        }
    }
/*    @GetMapping("")
    public Page<Reservation> getReservations(@RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(defaultValue = "id") String orderBy) {
        return reservationsService.getReservations(page, size, orderBy);
    }*/
    @GetMapping("")
    public List<Reservation> getReservations() {
        return reservationsService.getReservations();
    }
    @GetMapping("/{reservationId}")
    public Reservation findById(@PathVariable UUID reservationId) {
        return reservationsService.findById(reservationId);
    }
    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findAndDelete(@PathVariable UUID reservationId) {
        reservationsService.findByIdAndDelete(reservationId);
    }
}
