package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConsultDataDTO(
        Long doctorId,
        @NotNull Long patientId,
        @NotNull @Future LocalDateTime date,
        Specialty specialty
        ) {
}
