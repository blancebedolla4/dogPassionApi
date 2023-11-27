package com.dog.dogapi.service;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DogService {
    @Autowired
    private DogRepository dogRepository;
    public Iterable<Dog> getAllDogs() {
        return dogRepository.findAll();
    }
    public Optional<Dog> getDogById(Long id) {
        return dogRepository.findById(id);
    }

    public Dog createDog(Dog dog) {
        return dogRepository.save(dog);
    }
    public void deleteDogById(Long id) {
        dogRepository.deleteById(id);
    }
}

