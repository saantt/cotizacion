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
import com.inforcol.cotizacion.model.CoverageModel;
import com.inforcol.cotizacion.service.CoverageService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/coverage")
@Slf4j 
public class CoverageController {

    @Autowired
    private CoverageService coverageService;


    // GET ALL
    @GetMapping
    public ResponseEntity<List<CoverageResponseDto>> getAllCoverages() {
        log.info("CoverageController -> getAllCoverages()");
        List<CoverageResponseDto> coverages = coverageService.getAllCoverages();
        log.info("Coverages found: {}", coverages.size());
        return ResponseEntity.ok(coverages);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CoverageResponseDto> getCoverageById(@PathVariable String id) {

        log.info("CoverageController -> getCoverageById()");

        CoverageResponseDto coverage = coverageService.getCoverageById(id);

        if (coverage != null) {
            log.info("Coverage found. Id: {}", id);
        } else {
            log.info("No coverage found for id: {}", id);
        }

        return ResponseEntity.ok(coverage);
    }

    // POST
    @PostMapping
    public ResponseEntity<CoverageResponseDto> createCoverage(@RequestBody CoverageRequestDto dto) {

        log.info("CoverageController -> createCoverage()");

        CoverageResponseDto response = coverageService.createCoverage(dto);

        log.info("Coverage created successfully. Id: {}", response.getId_cobertura());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<CoverageResponseDto> updateCoverage(
            @PathVariable String id,
            @RequestBody CoverageUpdateRequestDto dto) {

        log.info("CoverageController -> updateCoverage()");
        log.info("Updating coverage with id: {}", id);

        CoverageResponseDto response = coverageService.updateCoverage(id, dto);

        log.info("Coverage updated successfully. Id: {}", response.getId_cobertura());

        return ResponseEntity.ok(response);
    }

    // CRUD
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoverage(@PathVariable String id) {

        log.info("CoverageController -> deleteCoverage()");
        log.info("Deleting coverage with id: {}", id);

        coverageService.deleteCoverage(id);

        log.info("Coverage deleted successfully. Id: {}", id);

        return ResponseEntity.noContent().build();
    }


}

