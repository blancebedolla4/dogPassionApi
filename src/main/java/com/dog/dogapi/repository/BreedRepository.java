package com.dog.dogapi.repository;

import com.dog.dogapi.model.Breed;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BreedRepository extends CrudRepository<Breed, Long> {
    List<Breed> findByDogId(Long dogId);
}
