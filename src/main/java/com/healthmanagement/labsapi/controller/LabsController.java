package com.healthmanagement.labsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LabsController {
    @GetMapping("/getlabs")
    public String getLabs(){
        return "Labs";
            }
}
