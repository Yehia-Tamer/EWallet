package com.example.EWallet.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name="WALLETS")

public class Wallet {
    private float balance;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID walletID;

    private String currency;

    @OneToOne(mappedBy = "wallet")
    private User user;

    @OneToMany(mappedBy = "recipientWallet")
    private List<Transaction> recipientTransactions;

    @OneToMany(mappedBy = "senderWallet")
    private List<Transaction> senderTransactions;


}
