package com.rhapp.rh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rhapp.rh.domain.models.Job;

import java.util.UUID;

@Repository
public interface JobsRepository extends JpaRepository<Job, UUID> {}
