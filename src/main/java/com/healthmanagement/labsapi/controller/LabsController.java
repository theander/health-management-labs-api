package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.service.LabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LabsController {
    private final LabService labService;
    @Operation(summary = "Get Labs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Lab",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Lab.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Lab", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Lab not found", content = @Content)
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Lab createLab(@RequestBody Lab lab) {
        return labService.createLab(lab);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Lab updateLab(@RequestBody Lab lab) {
        return labService.updateLab(lab);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Lab> getLab(@RequestBody Long userId) {
        return labService.getLabs(userId);
    }
}
