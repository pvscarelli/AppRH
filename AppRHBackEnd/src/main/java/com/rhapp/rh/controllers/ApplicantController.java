package com.rhapp.rh.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rhapp.rh.domain.dtos.ApplicantDto;
import com.rhapp.rh.domain.dtos.ApplicantsListDto;
import com.rhapp.rh.domain.models.Applicant;
import com.rhapp.rh.services.ApplicantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/v1/applicants")
@RestController
public class ApplicantController {

    @Autowired
    ApplicantService applicantService;
  
    @GetMapping
    public ResponseEntity<ApplicantsListDto> getAllApplicants() {
        ApplicantsListDto applicantsList = new ApplicantsListDto(applicantService.getAllApplicants());

        return ResponseEntity.ok(applicantsList); 
    }
    
    @GetMapping("/{cpf}")
    public ResponseEntity<ApplicantDto> getApplicant(@PathVariable("cpf") String cpf) {
        Applicant searchedApplicant = applicantService.getApplicant(cpf);

        ApplicantDto searchedApplicantDto = new ApplicantDto(searchedApplicant.getName(), searchedApplicant.getCpf(), searchedApplicant.getMail());

        return ResponseEntity.ok(searchedApplicantDto);
    }
    

    @PostMapping
    public ResponseEntity<Object> registerApplicant(@RequestBody ApplicantDto applicantDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        applicantService.registerApplicant(applicantDto);

        return ResponseEntity.ok(applicantDto);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<ApplicantDto> updateApplicant(@PathVariable("cpf") String cpf, @RequestBody ApplicantDto updateApplicantDto) {
        applicantService.updateApplicant(cpf, updateApplicantDto);
        
        return ResponseEntity.ok(updateApplicantDto);
    }
    
    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteApplicant(@PathVariable("cpf") String cpf ) {
        applicantService.deleteApplicant(cpf);

        return ResponseEntity.ok().build();
    }
}