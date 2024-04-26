package com.rhapp.rh.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhapp.rh.domain.dtos.ApplicantDto;
import com.rhapp.rh.domain.exceptions.ApplicantNotFoundException;
import com.rhapp.rh.domain.exceptions.DuplicatedCredentialsException;
import com.rhapp.rh.domain.models.Applicant;
import com.rhapp.rh.domain.models.Job;
import com.rhapp.rh.repositories.ApplicantsRepository;
import com.rhapp.rh.repositories.JobsRepository;

@Service
public class ApplicantService {
    
    @Autowired
    ApplicantsRepository applicantsRepository;

    @Autowired
    JobsRepository jobsRepository;

    public Iterable<Applicant> getAllApplicants() {
        Iterable<Applicant> applicantsList = applicantsRepository.findAll();

        return  applicantsList;
    }

    public Applicant getApplicant(String cpf) {
        Applicant searchedApplicant = applicantsRepository.findByCpf(cpf);
        if(searchedApplicant != null){
            return searchedApplicant;
        }
        
        throw new ApplicantNotFoundException();
    }

    public Applicant registerApplicant(ApplicantDto applicantDto) {
        Applicant newApplicant = new Applicant();
        BeanUtils.copyProperties(applicantDto, newApplicant);
        Applicant existingApplicant = applicantsRepository.findByCpfOrMail(newApplicant.getCpf(), newApplicant.getMail());
        if (existingApplicant != null){
            checkWhatCredetialIsDuplicated(existingApplicant, newApplicant);
        }

        return newApplicant;
    }

    public void checkWhatCredetialIsDuplicated(Applicant existingApplicant, Applicant newApplicant){
        if(existingApplicant.getCpf().equals(newApplicant.getCpf())){
            throw new DuplicatedCredentialsException("CPF duplicado.");
        } else {
            throw new DuplicatedCredentialsException("E-mail duplicado.");
        }
    }

    public Applicant updateApplicant(String cpf, ApplicantDto updateApplicantDto) {
        Applicant applicantToUpdate = applicantsRepository.findByCpf(cpf);
        if(applicantToUpdate != null){
            BeanUtils.copyProperties(updateApplicantDto, applicantToUpdate);
            applicantsRepository.save(applicantToUpdate);
            return applicantToUpdate;
        }

        throw new ApplicantNotFoundException();
    }

    public Applicant deleteApplicant(String cpf) {
        Applicant applicantToDelete = applicantsRepository.findByCpf(cpf);

        if(applicantToDelete != null){
            applicantsRepository.delete(applicantToDelete);
            return applicantToDelete;
        }

        throw new ApplicantNotFoundException();
    }
    
    public void removeJobFromApplicant(Applicant applicant, Job job){
        applicant.getJobs().remove(job);
        
        if(applicant.getJobs().isEmpty()){
            deleteApplicant(applicant.getCpf());
        }
    }

    public void addJobToApplicant(Applicant applicant, Job job){
        applicant.getJobs().add(job);
        applicantsRepository.save(applicant);
    }
}
