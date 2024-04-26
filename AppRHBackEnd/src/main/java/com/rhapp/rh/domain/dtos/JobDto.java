package com.rhapp.rh.domain.dtos;

import java.sql.Date;
import java.util.List;

import com.rhapp.rh.domain.models.Applicant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record JobDto(@Valid @NotBlank String name,
                @NotBlank String description,
                @NotNull Date expiration,
                @NotNull double salary,
                List<Applicant> applicants){}

