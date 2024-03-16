package com.rhapp.rh.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Applicants")
public class Applicant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idApplicant;

    private String cpf;
    private String applicantName;
    private String applicantEmail;

    @JsonIgnore
    @ManyToMany(mappedBy = "applicants")
    private List<Job> jobs = new ArrayList<>();

    public UUID getIdApplicant() {
        return idApplicant;
    }
    public void setIdApplicant(UUID idApplicant) {
        this.idApplicant = idApplicant;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getApplicantName() {
        return applicantName;
    }
    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }
    public String getApplicantEmail() {
        return applicantEmail;
    }
    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }
    public List<Job> getJobs() {
        return jobs;
    }
    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}

