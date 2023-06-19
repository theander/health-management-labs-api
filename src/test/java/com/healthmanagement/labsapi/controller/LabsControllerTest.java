package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.repository.LabRepository;
import com.healthmanagement.labsapi.service.LabService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LabsController.class)
@ActiveProfiles("test")
class LabsControllerTest {
    @MockBean
    private LabRepository labRepository;
    @MockBean
    private LabService labService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getListLab() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.content().);


    }

    @Test public void getNotFoundOnLabById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab/10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
    @Test public void getLabById() throws Exception {
        when(labService.getLabsById(any())).thenReturn(this.getLab());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab/10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    private static Lab getLab() {
        return Lab.builder().id(5l).username("jose").description("lab do jose").name("x-ray").build();
    }
//
//    @Test
//    void finishLab() {
//        assertThat(true).isTrue();
//    }
//
//    @Test
//    void getLab() {
//        assertThat(true).isTrue();
//    }
//
//    @Test
//    void getLabs() {
//        assertThat(true).isTrue();
//    }
//
//    @Test
//    void getLabsByUsername() {
//        assertThat(true).isTrue();
//    }
}