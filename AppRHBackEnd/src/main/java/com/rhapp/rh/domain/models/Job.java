package com.rhapp.rh.domain.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "jobs")
public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name="id")
    private UUID id;
    private String name;
    private String description;
    private Date expiration;
    private double salary;

    @ManyToMany
    @JoinTable(name = "jobs_applicants", joinColumns =  @JoinColumn(name = "job"), inverseJoinColumns = @JoinColumn(name = "applicant"))
    private List<Applicant> applicants = new ArrayList<>();
}
