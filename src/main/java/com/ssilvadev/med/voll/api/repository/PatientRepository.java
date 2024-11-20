package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("""
            select p.status
            from Patient p
            where
            p.id = :patientId
            """)
    boolean findStatusById(Long patientId);
}
