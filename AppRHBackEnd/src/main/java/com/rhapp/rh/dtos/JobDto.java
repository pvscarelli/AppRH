package com.rhapp.rh.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobDto(@Valid @NotBlank String jobName,
                @NotBlank String jobDescription,
                @NotBlank String jobDate,
                @NotNull @NotBlank String jobSalary) {

}
