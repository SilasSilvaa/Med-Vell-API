package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.DoctorRegisterDataDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/medicos")
public class DoctorController {

    @PostMapping
    public void register(@RequestBody DoctorRegisterDataDTO data){

    }
}
