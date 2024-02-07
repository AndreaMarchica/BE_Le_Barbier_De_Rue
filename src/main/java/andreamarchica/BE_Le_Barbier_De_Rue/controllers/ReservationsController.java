package andreamarchica.BE_Le_Barbier_De_Rue.controllers;

import andreamarchica.BE_Le_Barbier_De_Rue.entities.Reservation;
import andreamarchica.BE_Le_Barbier_De_Rue.exceptions.BadRequestException;
import andreamarchica.BE_Le_Barbier_De_Rue.payloads.NewReservationDTO;
import andreamarchica.BE_Le_Barbier_De_Rue.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
}
