package com.dog.dogapi.model;

import jakarta.persistence.*;

@Entity
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BREED_ID")
    private Long id;

    @Column(name = "BREED_NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "DOG_ID")
    private Dog dog;


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

    @Override
    public String toString() {
        return "Breed{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
