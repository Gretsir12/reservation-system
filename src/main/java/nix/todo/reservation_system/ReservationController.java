package nix.todo.reservation_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    //variables
    private final ReservationService reservationService;
    private static final Logger log = LoggerFactory.getLogger(ReservationController.class);
    //constructor
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //endpoints
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        log.info("Called getAllReservations");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id){
        log.info("Called getReservationById");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservationToCreate) {
        log.info("Called createReservation, reservationToCreate");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.createReservation(reservationToCreate));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservationToUpdate) {
        log.info("Called updateReservation");
        var updated = reservationService.updateReservation(id, reservationToUpdate);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        log.info("Called deleteReservation");
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .build();
        } catch (NoSuchElementException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }
}