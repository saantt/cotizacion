package com.inforcol.cotizacion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.inforcol.cotizacion.dto.coverage.CoverageRequestDto;
import com.inforcol.cotizacion.dto.coverage.CoverageResponseDto;
import com.inforcol.cotizacion.dto.coverage.CoverageUpdateRequestDto;
import com.inforcol.cotizacion.model.CoverageModel;

@Mapper(componentModel = "spring")
public interface CoverageMapper {

    CoverageResponseDto toResponse(CoverageModel coverage);

    CoverageModel toEntity(CoverageRequestDto dto);

    void updateEntity(CoverageUpdateRequestDto dto, 
                      @MappingTarget CoverageModel coverage);
}
