package com.inforcol.cotizacion.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.mapper.CoverageMapper;
import com.inforcol.cotizacion.model.CoverageModel;
import com.inforcol.cotizacion.repository.CoverageRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class CoverageServiceImpl implements CoverageService{

    private final CoverageRepository coverageRepository;
    private final CoverageMapper coverageMapper;

    // GET ALL
    @Override
    public List<CoverageResponseDto> getAllCoverages() {

        log.info("Fetching all coverages from database");
        List<CoverageModel> coverages = coverageRepository.findAll();
        log.info("Retrieved {} coverages", coverages.size());

        return coverages.stream()
                .map(coverageMapper::toResponse)
                .toList();
    }

    // GET BY NAME COVERAGE
    @Override
    public List<CoverageResponseDto> findByNombreCobertura(String name) {

        log.info("Fetching all coverages from database");
        List<CoverageModel> coverages = coverageRepository.findByNombreCobertura(name);
        log.info("Retrieved {} coverages", coverages.size());

        return coverages.stream()
                .map(coverageMapper::toResponse)
                .toList();
    }

    // GET GREATER RATE
    @Override
    public List<CoverageResponseDto> findGreaterRate(BigDecimal rate) {

        log.info("Fetching all coverages from database");
        List<CoverageModel> coverages = coverageRepository.findGreaterRate(rate);
        log.info("Retrieved {} coverages", coverages.size());

        return coverages.stream()
                .map(coverageMapper::toResponse)
                .toList();
    }

    // GET BY ID
    @Override
    public CoverageResponseDto getCoverageById(String id) {

        log.info("Searching coverage with id: {}", id);
        CoverageModel coverage = coverageRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Coverage not found with id: {}", id);
                    return new RuntimeException("Coverage not found with id: " + id);
                });
        log.info("Coverage found with id: {}", id);

        return coverageMapper.toResponse(coverage);
    }

    // POST
    @Override
    public CoverageResponseDto createCoverage(CoverageRequestDto dto) {

        log.info("Creating new coverage");
        CoverageModel coverage = coverageMapper.toEntity(dto);
        CoverageModel saved = coverageRepository.save(coverage);
        log.info("Coverage created successfully Id: {}", saved.getId_cobertura());
        return coverageMapper.toResponse(saved);
    }

    // PUT
    @Override
    public CoverageResponseDto updateCoverage(
            String id,
            CoverageUpdateRequestDto dto) {

        log.info("Updating coverage with id: {}", id);

        CoverageModel coverage = coverageRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Coverage not found with id: {}", id);
                    return new RuntimeException("Coverage not found with id: " + id);
                });

        coverage.setNombre_cobertura(dto.getNombre_cobertura());
        coverage.setTasa_publico(dto.getTasa_publico());
        coverage.setTasa_particular(dto.getTasa_particular());

        CoverageModel saved = coverageRepository.save(coverage);

        log.info("Coverage updated successfully. Id: {}", saved.getId_cobertura());

        return coverageMapper.toResponse(saved);
    }

    // DELETE
    @Override
    public void deleteCoverage(String id) {

        log.info("Deleting coverage with id: {}", id);
        CoverageModel coverage = coverageRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Coverage not found with id: {}", id);
                    return new RuntimeException("Coverage not found with id: " + id);
                });
        coverageRepository.delete(coverage);
        log.info("Coverage deleted successfully. Id: {}", id);
    }

    @Override
    public Page<CoverageResponseDto> getAllCoveragesPage(Pageable page){
        return coverageRepository.findAll(page).map(coverageMapper::toResponse);
    }

} 