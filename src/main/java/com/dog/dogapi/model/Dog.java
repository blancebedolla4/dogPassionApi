package com.dog.dogapi.model;

import jakarta.persistence.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Column(nullable = false)
    private String breed;

    @Column(nullable = false)
    private Integer age;
    @NotBlank
    @Column(nullable = false)
    private String owner;

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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }



    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Dog() {
    }

    public void setAge(int age) {
        this.age = age;
        // Add logging here to print the value of age
        System.out.println("Age set to: " + age);
    }
}
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "DOG_ID")
//    private Long id;
//
//    @Column(name = "DOG_NAME")
//    private String name;
//
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "BREED_ID")
//    private Reservation breed;
//
//    @OneToMany(mappedBy = "dog")
//    private List<Reservation> breeds;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Reservation getBreed() {
//        return breed;
//    }
//
//    public void setBreed(Reservation breed) {
//        this.breed = breed;
//    }
//
//    @Override
//    public String toString() {
//        return "Dog{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", breed=" + breed +
//                '}';
//    }
//}
