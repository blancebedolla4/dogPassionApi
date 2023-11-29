//package com.dog.dogapi.service;
//
//import com.dog.dogapi.model.Dog;
//import com.dog.dogapi.repository.DogRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DogService {
//    @Autowired
//    private DogRepository dogRepository;
//
//    public List<Dog> getAllDogs() {
//        Iterable<Dog> iterableDogs = dogRepository.findAll();
//        List<Dog> dogs = new ArrayList<>();
//        iterableDogs.forEach(dogs::add);
//        return dogs;
//    }
//
//    public Dog getDogById(Long id) {
//        return dogRepository.findById(id).orElse(null);
//    }
//
//    public Dog createDog(Dog dog) {
//        // Add validation logic if needed
//        return dogRepository.save(dog);
//    }
//
//    public Dog updateDog(Long id, Dog updatedDog) {
//        Dog existingDog = dogRepository.findById(id).orElse(null);
//        if (existingDog != null) {
//            // Update properties and save
//            existingDog.setName(updatedDog.getName());
//            existingDog.setBreed(updatedDog.getBreed());
//            existingDog.setAge(updatedDog.getAge());
//            existingDog.setOwner(updatedDog.getOwner());
//            // Add validation logic if needed
//            return dogRepository.save(existingDog);
//        }
//        return null;
//    }
//
//    public void deleteDog(Long id) {
//        dogRepository.deleteById(id);
//    }
//}

package com.dog.dogapi.service;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.model.Reservation;
import com.dog.dogapi.repository.DogRepository;
import com.dog.dogapi.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DogService.class);

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
        LOGGER.info("Updating dog with ID: {}", id);
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
    public List<Dog> searchDogs(String name, String breed) {
        LOGGER.info("Searching dogs with name: {} and breed: {}", name, breed);

        if (name != null && breed != null) {
            // Search by both name and breed
            return dogRepository.findByNameAndBreed(name, breed);
        } else if (name != null) {
            // Search by name
            return dogRepository.findByName(name);
        } else if (breed != null) {
            // Search by breed
            return dogRepository.findByBreed(breed);
        } else {
            // No search criteria provided, return all dogs
            return getAllDogs();
        }
    }
}





