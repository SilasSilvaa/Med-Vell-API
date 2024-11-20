package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDetailDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import com.ssilvadev.med.voll.api.repository.DoctorRepository;
import com.ssilvadev.med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientStatus implements IValidateAppointment {

    @Autowired
    private PatientRepository repository;

    @Override
    public void validate(ConsultDataDTO data){

        var patientActive = repository.findStatusById(data.patientId());

        if(!patientActive){
            throw new ValidateException("Appointments cannot be scheduled with an excluded patient");
        }
    }
}
