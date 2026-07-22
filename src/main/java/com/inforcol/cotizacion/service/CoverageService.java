package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.model.CoverageModel;
import com.inforcol.cotizacion.repository.CoverageRepository;

@Service
public class CoverageService {

    @Autowired
    private CoverageRepository coverageRepository;

    // GET ALL
    public List<CoverageModel> getAllCoverages(){
        return this.coverageRepository.findAll();
    }

    // GET BY ID
    public CoverageModel getCoverageById(String id) {
        return this.coverageRepository.findById(id)
                .orElse(null);
    }

    // CREATE
    public CoverageModel createCoverage(CoverageRequestDto dto) {

        CoverageModel coverage = new CoverageModel();

        coverage.setId_cobertura(dto.getId_cobertura());
        coverage.setNombre_cobertura(dto.getNombre_cobertura());
        coverage.setTasa_publico(dto.getTasa_publico());
        coverage.setTasa_particular(dto.getTasa_particular());

        CoverageModel savedCoverage = coverageRepository.save(coverage);

        return savedCoverage;
    }
}