package com.ssilvadev.med.voll.api.dto;

import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull Long id,
        String name,
        String phone,
        AddressDTO address
) { }