package com.example.EWallet.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name="TRANSACTIONS")

public class Transaction {
    @Id @GeneratedValue
    private UUID transactionID;

    private LocalDateTime transactionDateAndTime;

    private float amount;

    @ManyToOne
    @JoinColumn(name = "sender_wallet_ID")
    private Wallet senderWallet;

    @ManyToOne
    @JoinColumn(name = "recepient_wallet_ID")
    private Wallet recepientWallet;

    private TransactionType transactionType;

}
