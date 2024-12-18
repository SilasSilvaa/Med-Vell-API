package com.ssilvadev.med.voll.api.model;

import com.ssilvadev.med.voll.api.dto.DoctorRegisterDataDTO;
import com.ssilvadev.med.voll.api.dto.DoctorUpdateDataDTO;
import com.ssilvadev.med.voll.api.enums.Specialty;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    private Boolean status;

    public Doctor(DoctorRegisterDataDTO data){
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
        this.status = true;
    }

    public void updateInfo(DoctorUpdateDataDTO data) {

        if(data.name() != null) this.name = data.name();

        if(data.phone() != null) this.phone = data.phone();

        if(data.address() != null) {
            this.address.updateAddress(data.address());
        }

    }

    public void inactivate() {
        this.status = false;
    }
}
