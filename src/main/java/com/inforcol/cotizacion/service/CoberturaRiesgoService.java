package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoResponseDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CoberturaRiesgoService {

    List<CoberturaRiesgoResponseDto> getAllCoberturaRiesgo();

    CoberturaRiesgoResponseDto findByCoberturaRiesgoId(String idCotizacion, String idCobertura);

    CoberturaRiesgoResponseDto createCoberturaRiesgo(CoberturaRiesgoRequestDto request);

    CoberturaRiesgoResponseDto updateCoberturaRiesgo(String idCotizacion, String idCobertura, CoberturaRiesgoRequestDto request);

    void deleteCoberturaRiesgo(String idCotizacion, String idCobertura);

    Page<CoberturaRiesgoResponseDto> getAllCoberturaRiesgoPage(Pageable page);
}