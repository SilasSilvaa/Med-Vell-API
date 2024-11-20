package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.model.Consult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;


public interface ConsultRepository extends JpaRepository<Consult, Long> {

    boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date) ;

    boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstTimeHour, LocalDateTime lastTimeHour);
}
