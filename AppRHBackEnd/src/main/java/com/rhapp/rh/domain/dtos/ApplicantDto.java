package com.rhapp.rh.domain.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ApplicantDto(@Valid @NotBlank String cpf, @NotBlank String name, @NotBlank String mail) {

}