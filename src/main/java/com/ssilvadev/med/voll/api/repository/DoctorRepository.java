package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.model.Doctor;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByStatusTrue(Pageable pageable);

    @Query("""
            select d from Doctor d
            where
            d.status = true
            and
            d.specialty = :specialty
            and
            d.id not in(
                select c.doctor.id from Consult c
                where
                c.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor getRandomAvailableDoctor(Specialty specialty, @NotNull @Future LocalDateTime date);

    @Query("""
            select d.status
            from Doctor d
            where
            d.id = :doctorId
            """)
    boolean findStatusById(Long doctorId);
}
