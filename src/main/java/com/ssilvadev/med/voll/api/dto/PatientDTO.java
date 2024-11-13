package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.model.Patient;

public record PatientDTO(
        Long id,
        String name,
        String email,
        String cpf
) {

    public PatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
