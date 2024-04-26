package com.rhapp.rh.domain.exceptions;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(){
        super("Vaga não encontrada");
    }
}
