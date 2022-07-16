package com.healthmanagement.labsapi.controller;

import com.healthmanagement.labsapi.model.Lab;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LabsController {
    @Operation(summary = "Get Labs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Lab",      content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Lab.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Lab",  content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Lab not found", content = @Content)})
    @GetMapping("/getlabs")
    public String getLabs() {
        return "Labs";
    }
}
