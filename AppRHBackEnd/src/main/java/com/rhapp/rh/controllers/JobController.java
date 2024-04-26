package com.rhapp.rh.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rhapp.rh.domain.dtos.*;
import com.rhapp.rh.domain.models.*;
import com.rhapp.rh.services.JobService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RequestMapping("/v1/jobs")
@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public ResponseEntity<JobsListDto> getAllJobs() {
        JobsListDto jobsListDto = new JobsListDto(jobService.getAllJobs());

        return ResponseEntity.ok(jobsListDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable("id")  UUID id) {
        Job searchedJob = jobService.getJob(id);
        
        JobDto jobSearchedDto = new JobDto(searchedJob.getName(), searchedJob.getDescription(), searchedJob.getExpiration(), searchedJob.getSalary(), searchedJob.getApplicants());
        
        return ResponseEntity.ok(jobSearchedDto);
    }

    @PostMapping("/post")
    public ResponseEntity<Object> createJob(@RequestBody @Valid @NotNull JobDto postJobDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        jobService.createJob(postJobDto);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateJob(@PathVariable("id") UUID id, @RequestBody JobDto updateJobDto, BindingResult result) {
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        
        jobService.updateJob(id, updateJobDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteJob(@PathVariable("id") UUID id) {
        jobService.deleteJob(id);

        return ResponseEntity.ok().build(); 
    }

    @PostMapping("/add_applicant/{id}")
    public ResponseEntity<Object> addApplicantToJob(@PathVariable("id") UUID id, @RequestBody ApplicantDto applicantDto) {
        jobService.addApplicantToJob(id, applicantDto);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete_applicant/{id}/{cpf}")
    public ResponseEntity<Object> removeApplicantFromJob(@PathVariable("cpf") String cpf, @PathVariable("id") UUID id) {
        jobService.deleteApplicant(id, cpf);

        return ResponseEntity.ok().build();
    }
}
