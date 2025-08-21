package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class WalletNotFoundException extends RuntimeException{
    public WalletNotFoundException(UUID id) {
        super("Wallet with the id "+id+" is not found!");
    }
}
