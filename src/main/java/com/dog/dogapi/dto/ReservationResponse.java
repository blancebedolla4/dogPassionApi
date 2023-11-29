package com.dog.dogapi.dto;

import java.time.LocalDateTime;

public class ReservationResponse {

    private Long id;
    private Long dogId;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public ReservationResponse() {
        // Default constructor
    }

    public ReservationResponse(Long id, Long dogId, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        this.id = id;
        this.dogId = dogId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDogId() {
        return dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
}


