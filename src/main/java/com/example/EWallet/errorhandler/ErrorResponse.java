package com.example.EWallet.errorhandler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private final LocalDateTime dateTime;

    private final int status;

    private final String error;

    private final String message;

    private final String path;

    public ErrorResponse(int status,String error,String message,String path) {
        this.dateTime=LocalDateTime.now();
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }
}
