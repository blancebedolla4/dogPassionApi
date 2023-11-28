package com.dog.dogapi.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOG_ID")
    private Long id;

    @Column(name = "DOG_NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "BREED_ID")
    private Breed breed;

    @OneToMany(mappedBy = "dog")
    private List<Breed> breeds;

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
