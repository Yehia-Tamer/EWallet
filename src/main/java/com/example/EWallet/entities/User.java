package com.example.EWallet.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name="USERS")
public class User {

    @Column(unique = true)
    private String username;

    private String firstName;

    private String userType;

    private String lastName;

    @Column(unique = true)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String telephoneNumber;

    private String gender;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private int age;

    private LocalDate birthday;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wallet_id")

    private Wallet wallet;
}
