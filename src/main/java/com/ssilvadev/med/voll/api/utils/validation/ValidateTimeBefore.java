package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateTimeBefore implements IValidateAppointment{

    @Override
    public void validate(ConsultDataDTO data){
        var consultDate = data.date();
        var now = LocalDateTime.now();
        var minutesDifference = Duration.between(now, consultDate).toMinutes();

        if (minutesDifference < 30){
            throw new ValidateException("Appointments must be booked at least 30 minutes in advance");
        }

    }
}
