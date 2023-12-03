package com.dog.dogapi.service;
import com.dog.dogapi.dto.ReservationRequest;
import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private DogService dogService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);


    public List<Reservation> getReservationsByDogId(Long dogId) {
        return reservationRepository.findByDog_Id(dogId);
    }

    public Reservation createReservation(Long dogId, ReservationRequest reservationRequest) {
        Dog dog= dogService.getDogById(dogId);
        Reservation reservation = new Reservation();
        reservation.setDog(dog);
        reservation.setCheckInTime(reservationRequest.getCheckInTime());
        reservation.setCheckOutTime(reservationRequest.getCheckOutTime());
        return reservationRepository.save(reservation);
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

    public List<Reservation> getAllReservations() {
        Iterable<Reservation> reservationsIterable = reservationRepository.findAll();
        List<Reservation> reservations = new ArrayList<>();
        reservationsIterable.forEach(reservations::add);
        return reservations;
    }

    public void deleteReservation(Long id) {
        try {
            reservationRepository.deleteById(id);
            LOGGER.info("Reservation deleted successfully with ID: {}", id);
        } catch (Exception e) {
            LOGGER.error("Error deleting reservation with ID: {}", id, e);
        }
    }
}


//import com.dog.dogapi.exceptions.ReservationNotFoundException;
//import com.dog.dogapi.model.Dog;
//import com.dog.dogapi.model.Reservation;
//import com.dog.dogapi.repository.ReservationRepository;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class ReservationService {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    public List<Reservation> getReservationsByDogId(Long dogId) {
//        return reservationRepository.findByDog_Id(dogId);
//    }
//
//    public Reservation addReservation(Long dogId, Reservation reservation) {
//        Dog dog = new Dog();
//        dog.setId(dogId);
//        reservation.setDog(dog);
//        return reservationRepository.save(reservation);
//    }
//
//    public List<Reservation> getAllReservations() {
//        Iterable<Reservation> reservationsIterable = reservationRepository.findAll();
//
//        // Convert Iterable to List
//        List<Reservation> reservations = new ArrayList<>();
//        reservationsIterable.forEach(reservations::add);
//
//        return reservations;
//    }
//
//
//    public Reservation updateReservation(Long id, Reservation updatedReservation) {
//        LOGGER.info("Updating reservation with ID: {}", id);
//        Reservation existingReservation = reservationRepository.findById(id)
//                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
//        if (existingReservation != null) {
//            existingReservation.setCheckInTime(updatedReservation.getCheckInTime());
//            existingReservation.setCheckOutTime(updatedReservation.getCheckOutTime());
//            return reservationRepository.save(existingReservation);
//        }
//        return reservationRepository.save(existingReservation);
//    }
//
//    public void deleteReservation(Long id) {
//        reservationRepository.deleteById(id);
//    }
//}
//
