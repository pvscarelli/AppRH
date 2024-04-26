package com.rhapp.rh.domain.exceptions;

public class ApplicantNotFoundException extends RuntimeException{
    public ApplicantNotFoundException(){
        super("Candidato não encontrado");
    }
}
