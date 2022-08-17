package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.exception.customs.LabNotFoundException;
import com.healthmanagement.labsapi.model.Lab;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1")
public class LabsController {
    @Operation(summary = "Get Labs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Lab",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lab.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Lab", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Lab not found", content = @Content)
    })
    @PostMapping("/lab")
    public String createLab(@RequestBody String data) {

        return "Labs";
    }

    @PutMapping("/lab")
    public String updateLab(@RequestBody String data) {
        return "Labs";
    }

    @DeleteMapping("/lab")
    public String deleteLab(@RequestParam("id") String id) {
        return "Labs"+id;
    }

    @GetMapping("/lab")
    public String getLab(@RequestParam("id") String id) {
        return "Labs: "+id;
    }

}
