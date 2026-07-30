package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;
import com.inforcol.cotizacion.service.CoverageService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/coverage")
@Slf4j
@Tag(
        name = "Coverage",
        description = "API for coverage management."
)
public class CoverageController {

    @Autowired
    private CoverageService coverageService;

    // GET ALL
    @Operation(
        summary = "Get all coverages",
        description = "Get a list of all available coverage options."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Coverages obtain  successfully"
    )
    @GetMapping
    public ResponseEntity<List<CoverageResponseDto>> getAllCoverages() {
        log.info("getAllCoverages() - Request received");


        List<CoverageResponseDto> coverages = coverageService.getAllCoverages();
        log.info("getAllCoverages() - Request completed successfully");
        return ResponseEntity.ok(coverages);
    }

    // GET BY ID
    @Operation(
        summary = "Get coverage by id",
        description = "Get specific coverage using its unique identifier."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Coverage updated successfully"
    )

    @GetMapping("/{id}")
    public ResponseEntity<CoverageResponseDto> getCoverageById(@PathVariable String id) {

        log.info("getCoverageById/{} - Request received", id);

        CoverageResponseDto coverage = coverageService.getCoverageById(id);

        log.info("getCoverageById/{} - Request completed successfully", id);

        return ResponseEntity.ok(coverage);
    }

    // POST
    @Operation(
        summary = "Create coverage",
        description = "Creates a new coverage with the information provided in the request."
    )
    @ApiResponse(
        responseCode = "201",
        description = "Coverage created successfully"
    )
    @PostMapping
    public ResponseEntity<CoverageResponseDto> createCoverage(@Valid @RequestBody CoverageRequestDto dto) {

        log.info("createCoverage()- Request received");

        CoverageResponseDto response = coverageService.createCoverage(dto);

        log.info("createCoverage() - Request completed successfully");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT
    @Operation(
        summary = "Update coverage",
        description = "Updates an existing coverage using the provided identifier and data."
    )
    @ApiResponse(
        responseCode = "200",
        description = "Coverage updated successfully"
    )
    @PutMapping("/{id}")
    public ResponseEntity<CoverageResponseDto> updateCoverage(
            @Valid @PathVariable String id,
            @RequestBody CoverageUpdateRequestDto dto) {

        log.info("updateCoverage/{} - Request received", id);

        CoverageResponseDto response = coverageService.updateCoverage(id, dto);

        log.info("updateCoverage/{} - Request completed successfully", id);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @Operation(
        summary = "Delete coverage",
        description = "Deletes an existing coverage using its unique identifier."
    )
    @ApiResponse(
        responseCode = "204",
        description = "Coverage deleted successfully"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoverage(@PathVariable String id) {

        log.info("deleteCoverage/{} - Request received", id);

        coverageService.deleteCoverage(id);

        log.info("updateCoverage/{} - Request completed successfully", id);

        return ResponseEntity.noContent().build();
    }

}
