package com.rhapp.rh.domain.exceptions;

public class DuplicatedCredentialsException extends RuntimeException{
    public DuplicatedCredentialsException(String message){
        super(message);
    }
}
