package com.dog.dogapi.repository;

import com.dog.dogapi.model.Reservation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByDog_Id(Long dogId);
}
