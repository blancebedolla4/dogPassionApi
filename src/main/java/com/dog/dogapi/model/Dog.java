package com.dog.dogapi.model;

import jakarta.persistence.*;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOG_ID")
    private Long id;

    @Column(name = "DOG_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "BREED_ID")
    private Breed breed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed=" + breed +
                '}';
    }
}
