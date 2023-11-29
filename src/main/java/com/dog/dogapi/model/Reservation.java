package com.dog.dogapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import javax.annotation.Nullable;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "dog_id", nullable = false)
    private Dog dog;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime checkInTime = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime checkOutTime;

    @Column(nullable = true)
    private Integer age;


    public Reservation() {
    }

    // Constructor without @Nullable annotation for age parameter
    public Reservation(Long id, Dog dog, LocalDateTime checkInTime, LocalDateTime checkOutTime, @Nullable Integer age) {
        this.id = id;
        this.dog = dog;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.age = age;
    }

    @Nullable
    public Integer getAge() {
        return age;
    }

    public void setAge(@Nullable Integer age) {
        this.age = age;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
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
