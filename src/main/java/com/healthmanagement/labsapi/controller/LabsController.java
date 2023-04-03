package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.service.LabService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LabsController {
    private final LabService labService;

    @PostMapping("/lab")
    public ResponseEntity<Lab> createLab(@RequestBody Lab lab) {
        Lab savedLab = labService.createLab(lab);

        return new ResponseEntity<Lab>(savedLab, HttpStatus.OK);
    }

    @PutMapping("/lab/{id}/done")
    public ResponseEntity finishLab(@PathVariable(value = "id") Long id) {
        Lab updated = labService.finishLab(id);
        if (updated == null) {
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/lab/{id}")
    public ResponseEntity<Lab> getLab(@PathVariable(value = "id") Long id) {

        Lab labsById = labService.getLabsById(id);
        if (labsById == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(labsById, HttpStatus.OK);
    }

    @GetMapping("/lab")
    public ResponseEntity<List<Lab>> getLabs() {
        List<Lab> labs = labService.getLabs();
        if (labs == null || labs.size() == 0) {
            return new ResponseEntity<List<Lab>>(new ArrayList<Lab>(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Lab>>(labs, HttpStatus.OK);
    }
}
