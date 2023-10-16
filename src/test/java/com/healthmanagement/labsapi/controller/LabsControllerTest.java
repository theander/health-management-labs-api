package com.healthmanagement.labsapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.service.LabService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LabsController.class)
@ActiveProfiles("test")
public class LabsControllerTest {

    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private LabService labService;
    @Autowired
    private MockMvc mockMvc;

    private static Lab getLab() {
        return Lab.builder().id(5l).username("jose").description("lab do jose").name("x-ray").build();
    }

    @Test
    public void createLab() throws Exception {
        Lab build = Lab.builder().id(12l).name("Teste").build();
        Lab content = Lab.builder().name("Teste").build();

        String contentAsString = objectMapper.writeValueAsString(content);

        when(labService.createLab(content)).thenReturn(build);

        mockMvc.perform(post("/api/lab")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(contentAsString)
                ).andExpect(status().isCreated())
                .andReturn();
        verify(labService, times(1)).createLab(any());
    }

    @Test
    public void getNotFoundWhenfinishLab() throws Exception {
        when(labService.finishLab(3l)).thenReturn(null);

        mockMvc.perform(put("/api/lab/{id}/done", 3l)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void finishLab() throws Exception {
        when(labService.finishLab(anyLong())).thenReturn(Lab.builder().id(3L).name("x-ray").build());
        String id = "3";
        mockMvc.perform(put("/api/lab/{id}/done", id)

        ).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void getListLab() throws Exception {
        List<Lab> labs = new ArrayList<>();
        labs.add(Lab.builder().id(10l).name("Teste").username("joao").build());
        labs.add(Lab.builder().id(11l).name("x-ray").username("pedro").build());

        when(labService.getLabs(any(), any(), any())).thenReturn(labs);

        mockMvc.perform(get("/api/lab")
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Teste"))
                .andExpect(jsonPath("$.[1].username").value("pedro"))
                .andReturn();

        verify(labService, times(1)).getLabs(any(), any(), any());
    }

    @Test
    void shouldReturnEmptyArrayWhenGetListLab() throws Exception {
        List<Lab> labs = new ArrayList<>();

        when(labService.getLabs(any(), any(), any())).thenReturn(labs);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(labService, times(1)).getLabs(any(), any(), any());
    }

    @Test
    void shouldReturnEmptyArrayWhenGetListLabDBNull() throws Exception {

        when(labService.getLabs(any(), any(), any())).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        verify(labService, times(1)).getLabs(any(), any(), any());
    }

    @Test
    public void getNotFoundOnLabById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab/10")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }

    @Test
    public void getLabById() throws Exception {
        when(labService.getLabsById(any())).thenReturn(this.getLab());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/lab/10")
                        .accept(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("x-ray"))
                .andExpect(jsonPath("$.username").value("jose"))
                .andReturn();
        verify(labService, times(1)).getLabsById(any());
    }
    @Test public void getAnEmptyArray() throws Exception {
        when(labService.findLabByUsername(any())).thenReturn(new ArrayList<Lab>());
        mockMvc.perform(get("/api/lab/{username}/get-open-exams","anyName"))
                .andExpect(jsonPath("$").isEmpty())
                .andExpect(jsonPath("$").isArray())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test public void getLabsByUsername() throws Exception {
        List<Lab> labs = new ArrayList<>();
        labs.add(Lab.builder().id(10l).name("Teste").username("joao").build());
        labs.add(Lab.builder().id(11l).name("x-ray").username("pedro").build());
        when(labService.findLabByUsername(any())).thenReturn(labs);
        mockMvc.perform(get("/api/lab/{username}/get-open-exams","anyName"))
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(10))
                .andExpect(jsonPath("$[1].name").value("x-ray"))
                .andExpect(status().isOk())
                .andReturn();
    }
}