package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.PatientRegisterDTO;
import com.ssilvadev.med.voll.api.model.Patient;
import com.ssilvadev.med.voll.api.repository.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    public void register(@RequestBody @Valid  PatientRegisterDTO data){
        repository.save(new Patient(data));
    }
}
