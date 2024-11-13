package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.model.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByStatusTrue(Pageable pageable);
}
