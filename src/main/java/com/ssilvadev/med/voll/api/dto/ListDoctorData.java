package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.model.Doctor;

public record ListDoctorData(
        String name,
        String email,
        String crm,
        Specialty specialty
) {

    public ListDoctorData(Doctor doctor) {

        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
