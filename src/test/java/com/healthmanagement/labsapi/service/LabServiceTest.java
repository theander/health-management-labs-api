package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.model.Status;
import com.healthmanagement.labsapi.repository.LabRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LabServiceTest {
    @Mock
    LabRepository labRepository;
    @InjectMocks
    private LabService labService;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createLab() {
        Lab lab1 = this.dummyLab();
        Lab lab2 = this.dummyLab();
        lab2.setId(123l);

        when(labRepository.save(any())).thenReturn(lab2);

        Lab lab = labService.finishLab(lab1.getId());

        assertThat(lab.getId()).isNotNull();
        verify(labRepository,times(1)).save(any());

    }

    @Test
    void finishLab() {
        Lab lab1 = this.dummyLab();
        Lab lab2 = this.dummyLab();
        lab2.setId(123l);
        lab2.setStatus(Status.CLOSE);

        when(labRepository.findById(any())).thenReturn(Optional.ofNullable(lab1));
        when(labRepository.save(any())).thenReturn(lab2);

        Lab lab = labService.finishLab(any());

        assertThat(lab.getStatus()).isEqualTo(Status.CLOSE);

        verify(labRepository,times(1)).save(any());
        verify(labRepository,times(1)).findById(any());
    }

    @Test
    void getLabsById() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findById(any())).thenReturn(Optional.of(lab1));

        Lab byId = labService.getLabsById(any());

        assertThat(byId).isNotNull();
        assertThat(byId.getId()).isEqualTo(123);
        verify(labRepository,times(1)).findById(any());
    }
    @Test
    void getNullOnLabsById() {

        when(labRepository.findById(any())).thenReturn(Optional.empty());

        Lab byId = labService.getLabsById(any());

        assertThat(byId).isNull();
        verify(labRepository,times(1)).findById(any());
    }
    @Test
    void getLabsNameEmpty() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findAllByStatusEqualsAndUsernameEquals(any(),any())).thenReturn(Arrays.asList(lab1));

        List<Lab> labByUsername = labService.getLabs("OPEN","","");

        assertThat(labByUsername.size()).isGreaterThan(0);
        assertThat(labByUsername.get(0).getId()).isEqualTo(123);
        verify(labRepository,times(1)).findAllByStatusEqualsAndUsernameEquals(any(),any());
    }
    @Test
    void getLabsNameAndUsernameNull() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findByStatusEquals(any())).thenReturn(Arrays.asList(lab1));

        List<Lab> labByUsername = labService.getLabs("OPEN",null,null);

        assertThat(labByUsername.size()).isGreaterThan(0);
        assertThat(labByUsername.get(0).getId()).isEqualTo(123);
        verify(labRepository,times(1)).findByStatusEquals(any());
    }
    @Test
    void getLabsUsernameNull() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findAllByStatusEqualsAndNameEquals(any(),any())).thenReturn(Arrays.asList(lab1));

        List<Lab> labByUsername = labService.getLabs("OPEN",null,"john");

        assertThat(labByUsername.size()).isGreaterThan(0);
        assertThat(labByUsername.get(0).getId()).isEqualTo(123);
        verify(labRepository,times(1)).findAllByStatusEqualsAndNameEquals(any(),any());
    }
    @Test
    void findById() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findById(any())).thenReturn(Optional.of(lab1));

        Lab lab = labService.findById(123l);

        assertThat(lab).isNotNull();
        assertThat(lab.getId()).isEqualTo(123);
        verify(labRepository,times(1)).findById(any());
    }
    @Test
    void getNullOnFindById() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findById(any())).thenReturn(Optional.ofNullable(null));

        Lab lab = labService.findById(123l);

        assertThat(lab).isNull();
        verify(labRepository,times(1)).findById(any());
    }
    @Test
    void findLabByUsername() {
        Lab lab1 = this.dummyLab();
        lab1.setId(123l);

        when(labRepository.findByUsernameEqualsAndStatusEquals(any(),any())).thenReturn(Arrays.asList(lab1));

        List<Lab> labByUsername = labService.findLabByUsername("");

        assertThat(labByUsername.size()).isGreaterThan(0);
        assertThat(labByUsername.get(0).getId()).isEqualTo(123);
        verify(labRepository,times(1)).findByUsernameEqualsAndStatusEquals(any(),any());
    }
    @Test
    void getNullWhenFindLabByUsername() {

        when(labRepository.findByUsernameEqualsAndStatusEquals(any(),any())).thenReturn(null);

        List<Lab> labByUsername = labService.findLabByUsername("");

        assertThat(labByUsername).isNull();
        verify(labRepository,times(1)).findByUsernameEqualsAndStatusEquals(any(),any());
    }
private Lab dummyLab(){
        return Lab.builder().name("lab").username("max").status(Status.OPEN).build();
}

}