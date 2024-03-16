package com.rhapp.rh.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ApplicantDto(@Valid @NotBlank String cpf, @NotBlank String applicantName, @NotBlank String applicantEmail) {

}
