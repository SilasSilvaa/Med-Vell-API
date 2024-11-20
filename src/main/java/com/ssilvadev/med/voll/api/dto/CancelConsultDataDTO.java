package com.ssilvadev.med.voll.api.dto;

import com.ssilvadev.med.voll.api.enums.CancelReason;
import jakarta.validation.constraints.NotNull;

public record CancelConsultDataDTO(
        @NotNull
        Long ConsultId,

        @NotNull
        CancelReason reason
) {
}
