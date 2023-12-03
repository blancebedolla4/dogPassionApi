//package com.dog.dogapi.controller;
//import com.dog.dogapi.model.Dog;
//import com.dog.dogapi.service.DogService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;

//@RestController
//@RequestMapping("/api/dogs")
//public class DogController {
//    @Autowired
//    private DogService dogService;
//
//    @GetMapping
//    public List<Dog> getAllDogs() {
//        return dogService.getAllDogs();
//    }
//
//    @GetMapping("/{id}")
//    public Dog getDogById(@PathVariable Long id) {
//        return dogService.getDogById(id);
//    }
//
//    @PostMapping
//    public Dog createDog(@RequestBody Dog dog) {
//        return dogService.createDog(dog);
//    }
//
//    @PutMapping("/{id}")
//    public Dog updateDog(@PathVariable Long id, @RequestBody Dog updatedDog) {
//        return dogService.updateDog(id, updatedDog);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteDog(@PathVariable Long id) {
//        dogService.deleteDog(id);
//    }
//}
package com.dog.dogapi.controller;

import com.dog.dogapi.exceptions.DogNotFoundException;
import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.service.DogService;
import com.dog.dogapi.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/dogs")
@ControllerAdvice
public class DogController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DogController.class);

    @Autowired
    private DogService dogService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }

    @PostMapping
    public ResponseEntity<Dog> createDog(@Valid @RequestBody Dog dog) {
        Dog createdDog = dogService.createDog(dog);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDog.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdDog);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dog> getDogById(@PathVariable Long id) {
            Dog dog = dogService.getDogById(id);
            return ResponseEntity.ok(dog);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Dog> updateDog(@PathVariable Long id, @Valid @RequestBody Dog updatedDog) {
        Dog dog = dogService.getDogById(id);
            return ResponseEntity.ok(dog);
        }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDog(@PathVariable Long id) {
        dogService.deleteDog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/reservations")
    public ResponseEntity<List<Reservation>> getReservationsByDogId(@PathVariable Long id) {
        List<Reservation> reservations = dogService.getReservationsByDogId(id);
        return ResponseEntity.ok(reservations);
    }

//    @PostMapping("/{id}/reservations")
//    public ResponseEntity<Reservation> addReservation(@PathVariable Long id, @Valid @RequestBody Reservation reservation) {
//        Reservation addedReservation = reservationService.createReservation(id, reservation);
//        return ResponseEntity.status(HttpStatus.CREATED).body(addedReservation);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<Dog>> searchDogs(@RequestParam(name = "name", required = false) String name,
                                                @RequestParam(name = "breed", required = false) String breed) {
        try {
            LOGGER.info("Searching dogs with name: {} and breed: {}", name, breed);
            List<Dog> dogs = dogService.searchDogs(name, breed);
            return ResponseEntity.ok(dogs);
        } catch (DogNotFoundException e) {
            LOGGER.warn("No dogs found", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            LOGGER.error("Error occurred during dog search", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}