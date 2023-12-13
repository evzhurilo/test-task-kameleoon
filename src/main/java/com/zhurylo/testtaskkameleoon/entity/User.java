package com.zhurylo.testtaskkameleoon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "profiles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    //TODO necessary to solve problem with autogenerated id, when I add users through sql scripts and then try to create user using controller
    // appears a problem with id generation
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", initialValue = 8, sequenceName = "profiles_seq")
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;

    @OneToMany(mappedBy = "user")
    private Set<Quote> quotes;
}
