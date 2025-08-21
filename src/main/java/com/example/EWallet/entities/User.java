package com.example.EWallet.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Table(name="USERS")
public class User {

    private String username, firstName, userType, lastName, password, email, telephoneNumber, gender;

    @Id @GeneratedValue
    private UUID id;

    private int age;

    private LocalDate birthday;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="wallet_id")

    private Wallet wallet;
}
