package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.model.Doctor;

public record ListDoctorDTO(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {

    public ListDoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
