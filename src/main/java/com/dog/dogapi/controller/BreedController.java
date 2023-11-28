package com.dog.dogapi.controller;

import com.dog.dogapi.model.Breed;
import com.dog.dogapi.service.BreedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/breeds")
public class BreedController {
    @Autowired
    BreedService breedService;

    @RequestMapping(value = "/dogs/{dogId}/breeds", method = RequestMethod.GET)
    public Iterable<Breed> getAllBreeds(@PathVariable Long dogId) {
        return breedService.getBreedsByDogId(dogId);
    }
}



