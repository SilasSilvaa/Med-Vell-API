package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.Specialty;

public record DoctorRegisterDataDTO(String name, String email,
                                    String crm, Specialty specialty, Address address) {
}
