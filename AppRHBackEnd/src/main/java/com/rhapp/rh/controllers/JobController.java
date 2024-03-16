package com.rhapp.rh.controllers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rhapp.rh.dtos.*;
import com.rhapp.rh.models.*;
import com.rhapp.rh.repositories.*;

import jakarta.validation.Valid;

@RestController
public class JobController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicantRepository applicantRepository;

    @GetMapping("/jobs")
    public List<Job> showJobs() {
        return jobRepository.findAll();
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<Object> jobDetails(@NonNull @PathVariable("id")  UUID id) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            return ResponseEntity.ok(job);
        }
        return ResponseEntity.badRequest().body("Vaga não encontrada");
    }

    @PostMapping("/postJob")
    public ResponseEntity<Object> postForm(@Valid @NonNull JobDto jobDto, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Job job = new Job();
        BeanUtils.copyProperties(jobDto, job);
        jobRepository.save(job);
        return ResponseEntity.created(URI.create("/jobs/" + job.getId())).build();
    }

    
    @PostMapping("/addApplicant/{id}")
    public ResponseEntity<Object> addApplicant(@NonNull @PathVariable(value = "id") UUID id, @Valid @NonNull ApplicantDto applicantDto,
        BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        Applicant applicant = new Applicant();
        BeanUtils.copyProperties(applicantDto, applicant);
        
        Applicant existingApplicant = applicantRepository.findByCpf(applicant.getCpf());
        
        if (existingApplicant != null) {
            return addExistingApplicantToJob(id, existingApplicant);
        } else {
            return addNewApplicantToJob(id, applicant);
        }
    }

    public ResponseEntity<Object> addExistingApplicantToJob(UUID jobId, Applicant existingApplicant) {
    Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
        if (job.getApplicants().contains(existingApplicant)) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "CPF duplicado");
            return ResponseEntity.badRequest().body(responseBody);
        }
            job.getApplicants().add(existingApplicant);
            existingApplicant.getJobs().add(job);
            jobRepository.save(job);
            
            return ResponseEntity.created(URI.create("/applicants/" + existingApplicant.getCpf())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }

    public ResponseEntity<Object> addNewApplicantToJob(UUID jobId, Applicant newApplicant) {
        Optional<Job> jobOptional = jobRepository.findById(jobId);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.getApplicants().add(newApplicant);
            newApplicant.getJobs().add(job);
            applicantRepository.save(newApplicant);
            jobRepository.save(job);
            return ResponseEntity.created(URI.create("/applicants/" + newApplicant.getCpf())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }

    @PutMapping("/editJob/{id}")
    public ResponseEntity<Object> updateJob(@NonNull @PathVariable(value = "id") UUID id, @NonNull @Valid JobDto jobDto,
            BindingResult result, RedirectAttributes attributes) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            BeanUtils.copyProperties(jobDto, job);
            jobRepository.save(job);
            return ResponseEntity.created(URI.create("/jobs/" + job.getId())).build();
        }
        return ResponseEntity.badRequest().body("Job not found");
    }

    @DeleteMapping("/deleteJob/{id}")
    public ResponseEntity<Object> deleteJobById(@NonNull @PathVariable(value = "id") UUID id) {
        System.out.println("Chegou aqui");
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            jobRepository.delete(job);
            return ResponseEntity.status(HttpStatus.OK).build(); 
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found.");
    }
    
    @DeleteMapping("/deleteApplicant/{cpf}/{id}")
    public ResponseEntity<Object> deleteApplicantByCpf(@NonNull @PathVariable(value = "id") UUID id, @PathVariable(value = "cpf") String cpf ) {
        Applicant applicant = applicantRepository.findByCpf(cpf);
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            if(job.getApplicants().contains(applicant)){
                job.getApplicants().remove(applicant);
                applicant.getJobs().remove(job);
                jobRepository.save(job);
                if(applicant.getJobs().isEmpty()){
                    applicantRepository.delete(applicant);
                }
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.badRequest().body("Candidato não encontrado");
    }
}
