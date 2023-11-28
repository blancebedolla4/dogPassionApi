package com.dog.dogapi.controller;

import com.dog.dogapi.model.Dog;
import com.dog.dogapi.service.DogService;
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

    @PostMapping
    public ResponseEntity<?> createDog(@Valid @RequestBody Dog dog) {
        dog = dogService.createDog(dog);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newDogUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dog.getId()).toUri();
        responseHeaders.setLocation(newDogUri);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @GetMapping
    public Iterable<Dog> getAllDogs() {
        return dogService.getAllDogs();
    }
}




