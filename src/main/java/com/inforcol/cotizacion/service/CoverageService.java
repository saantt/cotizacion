package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;
import com.inforcol.cotizacion.mapper.CoverageMapper;
import com.inforcol.cotizacion.model.CoverageModel;
import com.inforcol.cotizacion.repository.CoverageRepository;

@Service
public class CoverageService {

    @Autowired
    private CoverageRepository coverageRepository;

    @Autowired
    private CoverageMapper coverageMapper;

    // GET ALL
    public List<CoverageResponseDto> getAllCoverages() {

        List<CoverageModel> coverages = coverageRepository.findAll();

        return coverages.stream()
                .map(coverageMapper::toResponse)
                .toList();
    }

    // GET BY ID
    public CoverageResponseDto getCoverageById(String id) {

        CoverageModel coverage = coverageRepository.findById(id)
                .orElse(null);

        if (coverage == null) {
            return null;
        }

        return coverageMapper.toResponse(coverage);
    }

    // POST
    public CoverageResponseDto createCoverage(CoverageRequestDto dto) {

        CoverageModel coverage = coverageMapper.toEntity(dto);
        CoverageModel saved = coverageRepository.save(coverage);

        return coverageMapper.toResponse(saved);
    }

    // PUT
    public CoverageResponseDto updateCoverage(
            String id,
            CoverageUpdateRequestDto dto) {

        CoverageModel coverage = coverageRepository.findById(id)
                .orElseThrow(() -> 
                    new RuntimeException("Coverage no encontrada con id: " + id));


        coverage.setNombre_cobertura(dto.getNombre_cobertura());
        coverage.setTasa_publico(dto.getTasa_publico());
        coverage.setTasa_particular(dto.getTasa_particular());


        CoverageModel saved = coverageRepository.save(coverage);

        return coverageMapper.toResponse(saved);
    }

    // DELETE
    public void deleteCoverage(String id) {

        CoverageModel coverage = coverageRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Coverage no encontrada con id: " + id));

        coverageRepository.delete(coverage);
    }

}