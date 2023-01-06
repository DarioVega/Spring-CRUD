package com.example.CrudUser.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30,nullable = false)
    private String name;
    @Column(length = 30,nullable = false)
    private String lastName;
    @Column(length = 30,nullable = false)
    private String nationality;

    public User(String name, String lastName, String nationality) {
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
    }
}

