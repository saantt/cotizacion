package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoResponseDto;

public interface CoberturaRiesgoService {

    List<CoberturaRiesgoResponseDto> getAllCoberturaRiesgo();

    CoberturaRiesgoResponseDto findByCoberturaRiesgoId(String idCotizacion, String idCobertura);

    CoberturaRiesgoResponseDto createCoberturaRiesgo(CoberturaRiesgoRequestDto request);

    CoberturaRiesgoResponseDto updateCoberturaRiesgo(String idCotizacion, String idCobertura, CoberturaRiesgoRequestDto request);

    void deleteCoberturaRiesgo(String idCotizacion, String idCobertura);
}