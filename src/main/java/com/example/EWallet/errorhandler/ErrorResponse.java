package com.example.EWallet.errorhandler;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private LocalDateTime dateTime;

    private int status;

    private String error;

    private String message;

    private String path;

    public ErrorResponse(LocalDateTime dateTime,int status,String error,String message,String path) {
        this.dateTime=dateTime;
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }
}
