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
    public Iterable<Breed> getAllBreeds() {
        return breedRepository.findAll();
    }
    public Optional<Breed> getBreedById(Long id) {
        return breedRepository.findById(id);
    }
    public Breed createBreed(Breed breed) {
        return breedRepository.save(breed);
    }
    public void deleteBreedById(Long id) {
        breedRepository.deleteById(id);
    }
}


