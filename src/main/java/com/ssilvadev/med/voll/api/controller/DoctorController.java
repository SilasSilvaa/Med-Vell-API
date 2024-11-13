package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.*;
import com.ssilvadev.med.voll.api.model.Doctor;
import com.ssilvadev.med.voll.api.repository.DoctorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("api/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DoctorDetailDTO> register(@RequestBody @Valid DoctorRegisterDataDTO data, UriComponentsBuilder builder){
        var doctor = repository.save(new Doctor(data));
        var uri = builder.path("/api/medicos/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DoctorDetailDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDoctorDTO>> getDoctors(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable){
        var doctors = repository.findAllByStatusTrue(pageable).map(ListDoctorDTO::new);
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetailDTO> getDoctor(@PathVariable Long id){
        var getDoctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailDTO(getDoctor));

    }

    @PutMapping
    @Transactional
    public ResponseEntity<DoctorDetailDTO> update(@RequestBody @Valid DoctorUpdateDataDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateInfo(data);

        return ResponseEntity.ok(new DoctorDetailDTO(doctor));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.inactivate();

        return ResponseEntity.noContent().build();
    }

}
