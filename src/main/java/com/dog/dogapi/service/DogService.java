package com.dog.dogapi.service;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.repository.DogRepository;
import com.dog.dogapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    public List<Dog> getAllDogs() {
        Iterable<Dog> iterableDogs = dogRepository.findAll();
        List<Dog> dogs = new ArrayList<>();
        iterableDogs.forEach(dogs::add);
        return dogs;
    }

    public Dog getDogById(Long id) {
        return dogRepository.findById(id).orElse(null);
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }

    public Dog updateDog(Long id, Dog updatedDog) {
        Dog existingDog = dogRepository.findById(id).orElse(null);
        if (existingDog != null) {
            // Update properties and save
            existingDog.setName(updatedDog.getName());
            existingDog.setBreed(updatedDog.getBreed());
            existingDog.setAge(updatedDog.getAge());
            existingDog.setOwner(updatedDog.getOwner());
            // Add validation logic if needed
            return dogRepository.save(existingDog);
        }
        return null;
    }

    public void deleteDog(Long id) {
        dogRepository.deleteById(id);
    }


    public List<Reservation> getReservationsByDogId(Long dogId) {
        // Implement logic to retrieve reservations by dog ID from the repository
        return reservationRepository.findByDog_Id(dogId);
    }
}



//    public Dog createDog(Dog dog) {
//        return dogRepository.save(dog);
//    }
//    public Iterable<Dog> getAllDogs() {
//        return dogRepository.findAll();
//    }
//    public Optional<Dog> getDogById(Long id) {
//        return dogRepository.findById(id);
//    }
//
//    public void deleteDogById(Long id) {
//        dogRepository.deleteById(id);
//    }

//    public List<Dog> searchDogsByName(String name) {
//        // Implement a search method in the repository
//        return dogRepository.findByNameContainingIgnoreCase(name);
//    }



