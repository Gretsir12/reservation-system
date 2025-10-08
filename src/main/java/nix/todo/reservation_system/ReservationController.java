package nix.todo.reservation_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservationToCreate) {
        log.info("Called createReservation, reservationToCreate");
        var reservation = ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reservationService
                .createReservation(reservationToCreate));
        return reservation;
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
        reservationService.deleteReservation(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Reservation> approveReservation(@PathVariable Long id) {
        log.info("Called approveReservation");
        var reservation = reservationService.approveReservation(id);
        return ResponseEntity
                .ok(reservation);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Reservation> cancelReservation(@PathVariable Long id) {
        log.info("Called cancelReservation");
        var reservation = reservationService.cancelReservation(id);
        return ResponseEntity
            .ok(reservation);
    }
}
