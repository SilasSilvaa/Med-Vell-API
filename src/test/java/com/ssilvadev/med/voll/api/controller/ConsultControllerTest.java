package com.ssilvadev.med.voll.api.controller;

import com.ssilvadev.med.voll.api.dto.ConsultDataDTO;
import com.ssilvadev.med.voll.api.dto.ConsultDetailDataDTO;
import com.ssilvadev.med.voll.api.enums.Specialty;
import com.ssilvadev.med.voll.api.service.ConsultService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultDataDTO> consultAppointmentJson;

    @Autowired
    private JacksonTester<ConsultDetailDataDTO> consultDetailJson;

    @MockBean
    private ConsultService consultService;

    @Test
    @DisplayName("Should be return code http 400 when the information is invalid")
    @WithMockUser
    void bookingScene1() throws Exception {



        var response = mvc.perform(
                    post("api/consultas").contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should be return code http 200 when the information is valid")
    @WithMockUser
    void bookingScene2() throws Exception {
        var date = LocalDateTime.now().plusHours(1);
        Specialty specialty = Specialty.CARDIOLOGIA;

        var consultDetailData = new ConsultDetailDataDTO(null, 2l, 5l, date);

         when(consultService.booking(any())).thenReturn(consultDetailData);

        var response = mvc.perform(post("api/consultas").content(consultAppointmentJson.write(
                        new ConsultDataDTO(2l, 5l, date, specialty)).getJson())
        ).andReturn().getResponse();


        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        var json = consultDetailJson.write(
            consultDetailData
        ).getJson();

        assertThat(response.getContentAsString()).isEqualTo(json);
    }
}