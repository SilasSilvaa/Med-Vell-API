package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import com.ssilvadev.med.voll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientAppointment implements IValidateAppointment{

    @Autowired
    private ConsultRepository repository;

    @Override
    public void validate(ConsultDataDTO data){
        var firstTimeHour = data.date().withHour(7);
        var lastTimeHour = data.date().withHour(18);

        var patientHasAnotherAppointment = repository.existsByPatientIdAndDateBetween(data.patientId(), firstTimeHour, lastTimeHour);

        if(patientHasAnotherAppointment){
            throw new ValidateException("Patient has another appointment on the current day");
        }
    }
}
