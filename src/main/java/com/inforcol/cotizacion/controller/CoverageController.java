package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.model.CoverageModel;
import com.inforcol.cotizacion.service.CoverageService;

import lombok.extern.slf4j.Slf4j;

@RestController
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
    // POST
    // PUT
    // DELETE
}
