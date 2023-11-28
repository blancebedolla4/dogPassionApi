package com.dog.dogapi.controller;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.service.DogService;
import com.dog.dogapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dogs")
public class DogController {
    @Autowired
    private DogService dogService;

    @Autowired
    private ReservationService reservationService;
    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @GetMapping("/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogService.getDogById(id);
    }

    @PostMapping
    public Dog createDog(@RequestBody Dog dog) {
        return dogService.createDog(dog);
    }

    @PutMapping("/{id}")
    public Dog updateDog(@PathVariable Long id, @RequestBody Dog updatedDog) {
        return dogService.updateDog(id, updatedDog);
    }

    @DeleteMapping("/{id}")
    public void deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
    }


    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByDogId(@PathVariable Long id) {
        List<Reservation> reservations = dogService.getReservationsByDogId(id);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @PostMapping("/{id}/reservations")
    public ResponseEntity<Reservation> addReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        Reservation addedReservation = reservationService.addReservation(id, reservation);
        return new ResponseEntity<>(addedReservation, HttpStatus.CREATED);
    }

}




//    @PostMapping
//    public ResponseEntity<?> createDog(@Valid @RequestBody Dog dog) {
//        dog = dogService.createDog(dog);
//
//        // Set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newDogUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dog.getId()).toUri();
//        responseHeaders.setLocation(newDogUri);
//
//        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public Iterable<Dog> getAllDogs() {
//        return dogService.getAllDogs();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
//        Optional<Dog> optionalDog = dogService.getDogById(id);
//
//        return optionalDog.map(dog -> new ResponseEntity<>(dog, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//}



