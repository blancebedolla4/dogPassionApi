package com.dog.dogapi.service;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getReservationsByDogId(Long dogId) {
        return reservationRepository.findByDog_Id(dogId);
    }

    public Reservation addReservation(Long dogId, Reservation reservation) {
        Dog dog = new Dog();
        dog.setId(dogId);
        reservation.setDog(dog);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        Iterable<Reservation> reservationsIterable = reservationRepository.findAll();

        // Convert Iterable to List
        List<Reservation> reservations = new ArrayList<>();
        reservationsIterable.forEach(reservations::add);

        return reservations;
    }


    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation != null) {
            existingReservation.setCheckInTime(updatedReservation.getCheckInTime());
            existingReservation.setCheckOutTime(updatedReservation.getCheckOutTime());
            return reservationRepository.save(existingReservation);
        }
        return null;
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}

