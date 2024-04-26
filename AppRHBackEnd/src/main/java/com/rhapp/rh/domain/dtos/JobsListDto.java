package com.rhapp.rh.domain.dtos;

import java.util.List;

import com.rhapp.rh.domain.models.Job;

import jakarta.validation.constraints.NotNull;

public record JobsListDto(@NotNull List<Job> jobs) {}