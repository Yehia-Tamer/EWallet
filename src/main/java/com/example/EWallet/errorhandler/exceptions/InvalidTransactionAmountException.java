package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class InvalidTransactionAmountException extends RuntimeException {
    public InvalidTransactionAmountException(float amount) {
        super("Invalid transaction amount: "+amount+" !");
    }
}
