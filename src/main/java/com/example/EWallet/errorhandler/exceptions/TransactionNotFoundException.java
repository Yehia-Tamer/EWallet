package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(UUID id) {
        super("Transaction with the id "+id+" is not found!");
    }
}
