package com.ssilvadev.med.voll.api.repository;

import com.ssilvadev.med.voll.api.dto.AddressDTO;
import com.ssilvadev.med.voll.api.dto.DoctorRegisterDataDTO;
import com.ssilvadev.med.voll.api.dto.PatientRegisterDTO;
import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.model.Consult;
import com.ssilvadev.med.voll.api.model.Doctor;
import com.ssilvadev.med.voll.api.model.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Should return null when only registered doctor is not available on the date")
    void getRandomAvailableDoctorScene1() {
        //giver ou arrange
        var nextMondatAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Medico", "medico@email.com", "123456", Specialty.CARDIOLOGIA);
        var patient = createPatient("Patient", "patient@email.com", "00000000000");
        createConsult(doctor, patient, nextMondatAt10);

        //when or act
        var availableDoctor =  doctorRepository.getRandomAvailableDoctor(Specialty.CARDIOLOGIA, nextMondatAt10);

        //then or assert
        assertThat(availableDoctor).isNull();
    }

    @Test
    @DisplayName("Should return doctor when a registered doctor is available on the date")
    void getRandomAvailableDoctorScene2() {
        var nextMondatAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);

        var doctor = createDoctor("Medico", "medico@email.com", "123456", Specialty.CARDIOLOGIA);

        var availableDoctor =  doctorRepository.getRandomAvailableDoctor(Specialty.CARDIOLOGIA, nextMondatAt10);
        assertThat(availableDoctor).isEqualTo(doctor);
    }

    private Patient createPatient(String name, String email, String cpf) {
        Patient patient = new Patient(patientData(name, email, cpf));
        em.persist(patient);
        return patient;
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        Doctor doctor = new Doctor(doctorData(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private void createConsult(Doctor doctor, Patient patient, LocalDateTime date){
        em.persist(new Consult(null, doctor, patient,date, null));
    }

    private DoctorRegisterDataDTO doctorData(String name, String email, String crm, Specialty specialty){
        return new DoctorRegisterDataDTO(name, email, "11900000000", crm, specialty, addressData());
    }

    private AddressDTO addressData() {
        return new AddressDTO("street", "neighborhood", "0000000", "city", "uf", "complement", "number");
    }

    private PatientRegisterDTO patientData(String name, String email, String cpf){
        return new PatientRegisterDTO(name, email, "11900000000", cpf, addressData());
    }
}

