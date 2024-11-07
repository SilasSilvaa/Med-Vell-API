package com.ssilvadev.med.voll.api.dto;

public record Address(String street,
                      String neighborhood,
                      String cep,
                      String city,
                      String uf,
                      String complement,
                      String number ) {
}
