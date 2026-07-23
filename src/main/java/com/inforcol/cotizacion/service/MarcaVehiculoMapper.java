package com.inforcol.cotizacion.service;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;
import com.inforcol.cotizacion.model.MarcaVehiculo;

@Mapper(componentModel = "spring")
public interface MarcaVehiculoMapper {

    // Convierte Entity -> Response DTO
    MarcaVehiculoResponseDto toDto(MarcaVehiculo marcaVehiculo);
 
    // Convierte Request DTO -> Entity
    MarcaVehiculo toEntity(MarcaVehiculoRequestDto dto);
 
    // Actualiza una entidad existente con los datos del DTO
    void updateEntityFromDto(MarcaVehiculoRequestDto dto, @MappingTarget MarcaVehiculo marcaVehiculo);

}
