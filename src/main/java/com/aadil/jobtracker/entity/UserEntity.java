package com.aadil.jobtracker.entity;


import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;

}