package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(UUID walletId) {
        super("Cannot transfer to the same user!");
    }
}
