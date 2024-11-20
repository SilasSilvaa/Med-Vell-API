package com.ssilvadev.med.voll.api.model;

import com.ssilvadev.med.voll.api.dto.PatientRegisterDTO;
import com.ssilvadev.med.voll.api.dto.UpdatePatientDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String phone;
    private boolean status;

    @Embedded
    private Address address;

    public Patient(PatientRegisterDTO data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
        this.status = true;
    }

    public void updateInfo(UpdatePatientDTO patient){
        if(patient.name() != null) this.name = patient.name();
        if(patient.phone() != null) this.phone = patient.phone();

        if(patient.address() != null) this.address.updateAddress(patient.address());
    }

    public void inactivate() {
        this.status = false;
    }
}
