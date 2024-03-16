package com.rhapp.rh.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhapp.rh.dtos.*;
import com.rhapp.rh.models.*;
import com.rhapp.rh.repositories.*;

import jakarta.validation.Valid;

@RestController
public class JobController {

    @Autowired
    private JobsRepository jobsRepository;
    @Autowired
    private ApplicantsRepository applicantsRepository;

    @GetMapping("/jobs")
    public ResponseEntity<Object> getAllJobs() {
        return ResponseEntity.ok(jobsRepository.findAll());
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Object> getJob(@NonNull @PathVariable("id")  UUID id) {
        Optional<Job> optionalJob = jobsRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.badRequest().body("Job not found");
    }
    @PostMapping("/postJob")
    public ResponseEntity<Object> createJob(@Valid @NonNull JobDto jobDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Job job = new Job();
        BeanUtils.copyProperties(jobDto, job);
        jobsRepository.save(job);
        return ResponseEntity.created(URI.create("/jobs/" + job.getId())).build();
    }
    @PostMapping("/addApplicant/{id}")
    public ResponseEntity<Object> createApplicant(@NonNull @PathVariable("id") UUID id, @Valid @NonNull ApplicantDto applicantDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Applicant applicant = new Applicant();
        BeanUtils.copyProperties(applicantDto, applicant);
        Applicant existingApplicant = applicantsRepository.findByCpf(applicant.getCpf());
        if (existingApplicant != null) {
            return addExistingApplicantToJob(id, existingApplicant);
        } else {
            return addNewApplicantToJob(id, applicant);
        }
    }
    public ResponseEntity<Object> addExistingApplicantToJob(UUID jobId, Applicant existingApplicant) {
    Optional<Job> optionalJob = jobsRepository.findById(jobId);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
        if (job.getApplicants().contains(existingApplicant)) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "CPF duplicado");
            return ResponseEntity.badRequest().body(responseBody);
        }
            job.getApplicants().add(existingApplicant);
            existingApplicant.getJobs().add(job);
            jobsRepository.save(job);
            return ResponseEntity.created(URI.create("/applicants/" + existingApplicant.getCpf())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }
    public ResponseEntity<Object> addNewApplicantToJob(UUID jobId, Applicant newApplicant) {
        Optional<Job> optionalJob = jobsRepository.findById(jobId);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            job.getApplicants().add(newApplicant);
            newApplicant.getJobs().add(job);
            applicantsRepository.save(newApplicant);
            jobsRepository.save(job);
            return ResponseEntity.created(URI.create("/applicants/" + newApplicant.getCpf())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }
    @PutMapping("/editJob/{id}")
    public ResponseEntity<Object> updateJob(@NonNull @PathVariable("id") UUID id, @NonNull @Valid JobDto jobDto, BindingResult result) {
        Optional<Job> optionalJob = jobsRepository.findById(id);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            BeanUtils.copyProperties(jobDto, job);
            jobsRepository.save(job);
            return ResponseEntity.created(URI.create("/jobs/" + job.getId())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }
    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<Object> deleteJob(@NonNull @PathVariable("id") UUID id) {
        Optional<Job> optionalJob = jobsRepository.findById(id);
        if (optionalJob.isPresent()) {
            Job job = optionalJob.get();
            jobsRepository.delete(job);
            return ResponseEntity.status(HttpStatus.OK).build(); 
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
    }
    
    @DeleteMapping("/deleteApplicant/{cpf}/{id}")
    public ResponseEntity<Object> deleteApplicant(@NonNull @PathVariable("id") UUID id, @PathVariable("cpf") String cpf ) {
        Applicant applicant = applicantsRepository.findByCpf(cpf);
        Optional<Job> optionalJob = jobsRepository.findById(id);
        if(optionalJob.isPresent()){
            Job job = optionalJob.get();
            if(job.getApplicants().contains(applicant)){
                job.getApplicants().remove(applicant);
                applicant.getJobs().remove(job);
                jobsRepository.save(job);
                if(applicant.getJobs().isEmpty()){
                    applicantsRepository.delete(applicant);
                }
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().body("Applicant not found");
    }
}
