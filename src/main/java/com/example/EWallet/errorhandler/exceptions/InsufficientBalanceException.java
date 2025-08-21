package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(UUID id,float balance,float amount) {
        super("Wallet " + id + " has insufficient balance. Current balance: "
                + balance + ", attempted transaction amount: " + amount);
    }
}
