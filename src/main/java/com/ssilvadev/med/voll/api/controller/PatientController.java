package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.*;
import com.ssilvadev.med.voll.api.model.Patient;
import com.ssilvadev.med.voll.api.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("api/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientDTO> register(@RequestBody @Valid  PatientRegisterDTO data, UriComponentsBuilder builder){
        var patient = repository.save(new Patient(data));
        var uri = builder.path("/api/pacientes/{id}").buildAndExpand(patient.getId()).toUri();

        return ResponseEntity.created(uri).body(new PatientDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientDTO>> getPatients(Pageable pageable){
        Page<PatientDTO> patient = repository.findAll(pageable).map(PatientDTO::new);

        return ResponseEntity.ok(patient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable Long id){
        var getPatient = repository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDTO(getPatient));

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PatientDTO> updatePatient(@RequestBody @Valid UpdatePatientDTO data){
        var patient = repository.getReferenceById(data.id());
        patient.updateInfo(data);

        return ResponseEntity.ok(new PatientDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
