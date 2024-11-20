package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import com.ssilvadev.med.voll.api.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorTimeConflict implements IValidateAppointment {

    @Autowired
    private ConsultRepository repository;

    @Override
    public void validate(ConsultDataDTO data){
        var doctorWithAnotherAppointment = repository.existsByDoctorIdAndDate(data.doctorId(), data.date());

        if(doctorWithAnotherAppointment){
            throw new ValidateException("Doctor already has another appointment at that time");
        }
    }
}
