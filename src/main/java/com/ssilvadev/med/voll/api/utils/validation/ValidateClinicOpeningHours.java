package com.ssilvadev.med.voll.api.utils.validation;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidateClinicOpeningHours implements IValidateAppointment {

    @Override
    public void validate(ConsultDataDTO data){
        var consultDate = data.date();

        var sunday = consultDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpeningClinic = consultDate.getHour() < 7;
        var afterClosingClinic = consultDate.getHour() > 18;

        if(sunday || afterClosingClinic || beforeOpeningClinic) {
            throw new ValidateException("Consult outside opening hours");
        }
    }
}
