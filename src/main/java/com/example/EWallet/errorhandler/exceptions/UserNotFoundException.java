package com.example.EWallet.errorhandler.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id) {
        super("User with the id "+id+" is not found!");
    }
}
