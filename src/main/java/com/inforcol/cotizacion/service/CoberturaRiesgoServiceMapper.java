package com.inforcol.cotizacion.service;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.CobeturaRiesgoDto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CobeturaRiesgoDto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.model.CoberturaRiesgo;

@Service
public class CoberturaRiesgoServiceMapper {

    // Entidad -> DTO de Respuesta
    public CoberturaRiesgoResponseDto toDto(CoberturaRiesgo entity) {
        if (entity == null) return null;

        CoberturaRiesgoResponseDto dto = new CoberturaRiesgoResponseDto();
        dto.setIdCotizacion(entity.getIdCotizacion());
        dto.setIdCobertura(entity.getIdCobertura());
        dto.setIdDeducible(entity.getIdDeducible());
        dto.setPrimaCobertura(entity.getPrimaCobertura());

        return dto;
    }

    // DTO de Petición -> Entidad
    public CoberturaRiesgo toEntity(CoberturaRiesgoRequestDto dto) {
        if (dto == null) return null;

        CoberturaRiesgo entity = new CoberturaRiesgo();
        entity.setIdCotizacion(dto.getIdCotizacion());
        entity.setIdCobertura(dto.getIdCobertura());
        entity.setIdDeducible(dto.getIdDeducible());
        entity.setPrimaCobertura(dto.getPrimaCobertura());

        return entity;
    }
}
