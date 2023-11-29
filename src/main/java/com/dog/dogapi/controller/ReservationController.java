
package com.dog.dogapi.controller;


import com.dog.dogapi.dto.ReservationRequest;
import com.dog.dogapi.dto.ReservationResponse;
import com.dog.dogapi.exceptions.DogNotFoundException;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Retrieve reservations for a specific dog based on the provided dog ID.
     *
     * @param dogId The ID of the dog.
     * @return A ResponseEntity containing the list of reservations.
     */
    @GetMapping("/dog/{dogId}")
    public ResponseEntity<List<Reservation>> getReservationsByDogId(@PathVariable Long dogId) {
        List<Reservation> reservations = reservationService.getReservationsByDogId(dogId);
        return ResponseEntity.ok(reservations);
    }

    /**
     * Add a reservation for a specific dog.
     *
     * @param id                  The ID of the dog.
     * @param reservationRequest The request body containing reservation details.
     * @return A ResponseEntity containing the created reservation.
     */
    @PostMapping("/{id}/reservations")
    public ResponseEntity<ReservationResponse> addReservation(
            @PathVariable Long id,
            @RequestBody @Valid ReservationRequest reservationRequest) {

        try {
            Reservation addedReservation = reservationService.createReservation(id, new Reservation());

            ReservationResponse response = new ReservationResponse(
                    addedReservation.getId(),
                    addedReservation.getDog().getId(),
                    addedReservation.getCheckInTime(),
                    addedReservation.getCheckOutTime()
                    // Map additional reservation details...
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (DogNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Update a reservation by ID.
     *
     * @param id                The ID of the reservation to be updated.
     * @param updatedReservation The updated reservation details.
     * @return A ResponseEntity containing the updated reservation.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        Reservation updated = reservationService.updateReservation(id, updatedReservation);
        return updated != null ?
                ResponseEntity.ok(updated) :
                ResponseEntity.notFound().build();
    }

    /**
     * Retrieve all reservations.
     *
     * @return A ResponseEntity containing the list of all reservations.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getAllReservations();
        return reservations.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(reservations);
    }

    /**
     * Delete a reservation by ID.
     *
     * @param id The ID of the reservation to be deleted.
     * @return A ResponseEntity indicating the success of the deletion.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Log the exception and handle it appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
