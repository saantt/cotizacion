package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
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
    public ResponseEntity<List<CoverageModel>> getAllCoverages() {
        log.info("CoverageController -> getAllCoverages()");
        List<CoverageModel> coverages = coverageService.getAllCoverages();
        log.info("Coverages found: {}", coverages.size());
        return ResponseEntity.ok(coverages);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CoverageModel> getCoverageById(@PathVariable String id) {
        log.info("CoverageController -> getCoverageById()");
        CoverageModel coverage = coverageService.getCoverageById(id);

        if (coverage != null) {
            log.info("Coverage found. Id: {}", id);
        } else {
            log.info("No coverage found for id: {}", id);
        }

        return ResponseEntity.ok(coverage);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<CoverageModel> createCoverage(@RequestBody CoverageRequestDto dto) {

        log.info("CoverageController -> createCoverage()");
        CoverageModel response = coverageService.createCoverage(dto);
        log.info("Coverage created successfully. Id: {}", response.getId_cobertura());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // POST
    // PUT
    // DELETE
}
