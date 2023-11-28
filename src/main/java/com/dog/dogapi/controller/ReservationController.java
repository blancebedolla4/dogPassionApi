
package com.dog.dogapi.controller;

import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/dog/{dogId}")
    public ResponseEntity<List<Reservation>> getReservationsByDogId(@PathVariable Long dogId) {
        List<Reservation> reservations = reservationService.getReservationsByDogId(dogId);
        return ResponseEntity.ok(reservations);
    }

    @PostMapping("/dog/{dogId}")
    public ResponseEntity<Reservation> createReservation(@PathVariable Long dogId, @RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.addReservation(dogId, reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReservation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        Reservation updated = reservationService.updateReservation(id, updatedReservation);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();

        if (reservations.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(reservations);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
