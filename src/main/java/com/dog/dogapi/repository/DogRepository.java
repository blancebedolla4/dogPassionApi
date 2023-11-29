package com.dog.dogapi.repository;

import com.dog.dogapi.model.Dog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends CrudRepository<Dog, Long> {
    List<Dog> findByNameAndBreed(String name, String breed);
    List<Dog> findByName(String name);
    List<Dog> findByBreed(String breed);
}
