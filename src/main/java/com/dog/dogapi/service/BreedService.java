package com.dog.dogapi.service;

import com.dog.dogapi.model.Breed;
import com.dog.dogapi.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BreedService {
    @Autowired
    private BreedRepository breedRepository;
    //create breeds
    public Breed createBreed(Breed breed) {
        return breedRepository.save(breed);
    }
    //get all breeds
    public Iterable<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }
    //get breed by id
    public Optional<Breed> getBreedById(Long id) {
        return breedRepository.findById(id);
    }
    //delete breed by id
    public void deleteBreedById(Long id) {
        breedRepository.deleteById(id);
    }
    public Iterable<Breed> getBreedsByDogId(Long dogId) {

        return breedRepository.findByDogId(dogId);
    }
}


