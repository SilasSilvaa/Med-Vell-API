package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.model.Address;
import com.ssilvadev.med.voll.api.model.Doctor;

public record DoctorDetailDTO(Long id, String name, String email, String crm, String phone, Specialty specialty, Address address) {
    public DoctorDetailDTO(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
