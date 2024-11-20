package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import com.ssilvadev.med.voll.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDoctorStatus implements IValidateAppointment {

    @Autowired
    private DoctorRepository repository;

    @Override
    public void validate(ConsultDataDTO data){
        if (data.doctorId() == null) {
            return;
        }

        var doctorActive = repository.findStatusById(data.doctorId());

        if(!doctorActive){
            throw new ValidateException("Appointments cannot be scheduled with an excluded doctor");
        }
    }
}
