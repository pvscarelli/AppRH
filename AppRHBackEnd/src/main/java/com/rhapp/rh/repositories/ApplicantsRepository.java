package com.rhapp.rh.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rhapp.rh.domain.models.Applicant;

import java.util.UUID;



@Repository
public interface ApplicantsRepository extends JpaRepository<Applicant, UUID> {
    Applicant findByMail(String mail);
    Applicant findByCpf(String cpf);

    @Query("SELECT a FROM Applicant a WHERE a.cpf = ?1 OR a.mail = ?2")
    Applicant findByCpfOrMail(String cpf, String email);

    default Applicant existsByCpfOrMail(String cpf, String email) {
        return findByCpfOrMail(cpf, email);
    }
}

