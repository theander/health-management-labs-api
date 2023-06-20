package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.service.LabService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LabsController {
    private final LabService labService;

    @PostMapping("/lab")
    public ResponseEntity<Lab> createLab(@RequestBody Lab lab) {
        Lab savedLab = labService.createLab(lab);

        return new ResponseEntity<Lab>(savedLab, HttpStatus.CREATED);
    }

    @PutMapping("/lab/{id}/done")
    public ResponseEntity finishLab(@PathVariable(value = "id") Long id) {
        Lab updated = labService.finishLab(id);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<List<Lab>> getLabs(@RequestParam(value = "status", required = false) String status,
                                             @RequestParam(value = "username", required = false) String username,
                                             @RequestParam(value = "name", required = false) String name
    ) {
        List<Lab> labs = labService.getLabs(status, username, name);
        if (labs == null || labs.size() == 0) {
            return new ResponseEntity<List<Lab>>(new ArrayList<Lab>(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Lab>>(labs, HttpStatus.OK);
    }

    @GetMapping("/lab/{username}/get-open-exams")
    public ResponseEntity<List<Lab>> getLabsByUsername(@PathVariable(value = "username") String username) {
        List<Lab> labs = labService.findLabByUsername(username);
        if (labs.size() == 0) {
            return new ResponseEntity<List<Lab>>(new ArrayList<Lab>(), HttpStatus.OK);
        }
        return new ResponseEntity<List<Lab>>(labs, HttpStatus.OK);
    }
}
