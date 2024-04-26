package com.rhapp.rh.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rhapp.rh.domain.dtos.ApplicantDto;
import com.rhapp.rh.domain.dtos.JobDto;
import com.rhapp.rh.domain.exceptions.JobNotFoundException;
import com.rhapp.rh.domain.models.Applicant;
import com.rhapp.rh.domain.models.Job;
import com.rhapp.rh.repositories.ApplicantsRepository;
import com.rhapp.rh.repositories.JobsRepository;

@Service
public class JobService {
    
    @Autowired
    JobsRepository jobsRepository;

    @Autowired
    private ApplicantService applicantService;

    @Autowired
    ApplicantsRepository applicantsRepository;

    public List<Job> getAllJobs() {
        Iterable<Job> jobsIterable = jobsRepository.findAll();
        List<Job> jobsList = new ArrayList<>();

        for (Job job : jobsIterable) {
            jobsList.add(job);
        }

        return jobsList;
    }

    public Job getJob(UUID id) {
        Optional<Job> optionalJob = jobsRepository.findById(id);
        
        if (optionalJob.isPresent()) {
            Job jobSearched = optionalJob.get();

            return jobSearched;
        } else {
            throw new JobNotFoundException();
        }
    }

    public Job createJob(JobDto postJobDto) {
        Job newJob = new Job();
        BeanUtils.copyProperties(postJobDto, newJob);
        jobsRepository.save(newJob);

        return newJob;
    }

    public Job updateJob(UUID id, JobDto updateJobDto) {        
        Job jobToUpdate = getJob(id);
        BeanUtils.copyProperties(updateJobDto, jobToUpdate);
        jobsRepository.save(jobToUpdate);

        return jobToUpdate;
    }

    public Job deleteJob(UUID id) {
        Job jobToDelete = getJob(id);
        jobsRepository.delete(jobToDelete);

        return jobToDelete; 
    }

    public void addApplicantToJob(UUID id, ApplicantDto applicantToAddDto){
        Applicant applicantToAdd = applicantService.registerApplicant(applicantToAddDto);
        Job job = getJob(id);

        job.getApplicants().add(applicantToAdd);

        applicantService.addJobToApplicant(applicantToAdd, job);
        
        jobsRepository.save(job);
    }

    public void deleteApplicant(UUID id, String cpf){
        Applicant applicantToRemove = applicantService.getApplicant(cpf);

        Job job = getJob(id);
        job.getApplicants().remove(applicantToRemove);
        jobsRepository.save(job);

        applicantService.removeJobFromApplicant(applicantToRemove, job);
    }
}
