package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;

public interface CoverageService {

    List<CoverageResponseDto> getAllCoverages();
    CoverageResponseDto getCoverageById(String id);
    CoverageResponseDto createCoverage(CoverageRequestDto dto);
    CoverageResponseDto updateCoverage(String id, CoverageUpdateRequestDto dto);
    void deleteCoverage(String id);
}
