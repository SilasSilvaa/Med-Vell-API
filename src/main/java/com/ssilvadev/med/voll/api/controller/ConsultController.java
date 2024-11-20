package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.CancelConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDetailDataDTO;
import com.ssilvadev.med.voll.api.service.ConsultService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultController {

    @Autowired
    private ConsultService consultService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultDetailDataDTO> booking(@RequestBody @Valid ConsultDataDTO data){
        ConsultDetailDataDTO booking = consultService.booking(data);

        return ResponseEntity.ok(booking);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelConsult(@RequestBody @Valid CancelConsultDataDTO data) {
        consultService.cancelConsult(data);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ConsultDetailDataDTO>> getConsults(){
        var consults = consultService.getConsults();

        return ResponseEntity.ok(consults);
    }
}
