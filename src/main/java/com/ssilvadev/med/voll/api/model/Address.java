package com.ssilvadev.med.voll.api.model;

import com.ssilvadev.med.voll.api.dto.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String neighborhood;
    private String cep;
    private String city;
    private String uf;
    private String complement;
    private String number;

    public Address(AddressDTO address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.cep = address.cep();
        this.city = address.city();
        this.uf = address.uf();
        this.complement = address.complement();
        this.number = address.number();
    }
}
