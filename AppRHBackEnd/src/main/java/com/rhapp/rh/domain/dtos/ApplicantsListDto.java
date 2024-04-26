package com.rhapp.rh.domain.dtos;

import com.rhapp.rh.domain.models.Applicant;

public record ApplicantsListDto(Iterable<Applicant> applicants) {}