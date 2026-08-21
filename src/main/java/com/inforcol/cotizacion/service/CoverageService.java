package com.inforcol.cotizacion.service;

import java.math.BigDecimal;
import java.util.List;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoverageService {

    List<CoverageResponseDto> getAllCoverages();
    CoverageResponseDto getCoverageById(String id);
    List<CoverageResponseDto> findByNombreCobertura(String name);
    List<CoverageResponseDto> findGreaterRate(BigDecimal rate);
    CoverageResponseDto createCoverage(CoverageRequestDto dto);
    CoverageResponseDto updateCoverage(String id, CoverageUpdateRequestDto dto);
    void deleteCoverage(String id);
    Page<CoverageResponseDto> getAllCoveragesPage(Pageable page);
}
