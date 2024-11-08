package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.DoctorRegisterDataDTO;
import com.ssilvadev.med.voll.api.dto.ListDoctorData;
import com.ssilvadev.med.voll.api.model.Doctor;
import com.ssilvadev.med.voll.api.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegisterDataDTO data){
        repository.save(new Doctor(data));
    }

    @GetMapping
    public List<ListDoctorData> getDoctors(){
        return repository.findAll().stream()
                .map(ListDoctorData::new).toList();
    }
}
