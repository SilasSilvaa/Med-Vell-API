package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
