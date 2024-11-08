package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
