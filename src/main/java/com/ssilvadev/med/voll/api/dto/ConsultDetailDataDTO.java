package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.model.Consult;

import java.time.LocalDateTime;

public record ConsultDetailDataDTO(
        Long id ,
        Long doctorId,
        Long patientId,
        LocalDateTime date) {

    public ConsultDetailDataDTO(Consult consult) {
        this(consult.getId(), consult.getDoctor().getId(), consult.getPatient().getId(), consult.getDate());
    }
}
