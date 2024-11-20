package com.ssilvadev.med.voll.api.service;

import com.ssilvadev.med.voll.api.dto.CancelConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDetailDataDTO;
import com.ssilvadev.med.voll.api.infra.exception.ValidateException;
import com.ssilvadev.med.voll.api.model.Consult;
import com.ssilvadev.med.voll.api.model.Doctor;
import com.ssilvadev.med.voll.api.repository.ConsultRepository;
import com.ssilvadev.med.voll.api.repository.DoctorRepository;
import com.ssilvadev.med.voll.api.repository.PatientRepository;
import com.ssilvadev.med.voll.api.utils.validation.IValidateAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {

    @Autowired
    private ConsultRepository consultRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<IValidateAppointment> validations;

    public List<ConsultDetailDataDTO> getConsults(){
        return consultRepository.findAll().stream().map(ConsultDetailDataDTO::new).toList();
    }

    public ConsultDetailDataDTO booking(ConsultDataDTO data){

        if(!patientRepository.existsById(data.patientId())) throw new ValidateException("Patient id is not exists");

        if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ValidateException("Doctor id is not exists");
        }

        validations.forEach(v -> v.validate(data));

        var doctor = chooseADoctor(data);
        if(doctor == null){
            throw new ValidateException("No doctor available on this date");
        }

        var patient = patientRepository.getReferenceById(data.patientId());
        var consult = new Consult(null, doctor, patient, data.date(),null);

        consultRepository.save(consult);

        return new ConsultDetailDataDTO(consult);
    }

    public void cancelConsult(CancelConsultDataDTO data) {
        if (!consultRepository.existsById(data.ConsultId())) {
            throw new ValidateException("Consult id not exists");
        }

        var consult = consultRepository.getReferenceById(data.ConsultId());
        consult.cancel(data.reason());
    }

    private Doctor chooseADoctor(ConsultDataDTO data) {
        if(data.doctorId() != null) return doctorRepository.getReferenceById(data.doctorId());

        if(data.specialty() == null) throw new ValidateException("The specialty is necessary when the doctor is not chosen");

        return doctorRepository.getRandomAvailableDoctor(data.specialty(), data.date());
    }
}
