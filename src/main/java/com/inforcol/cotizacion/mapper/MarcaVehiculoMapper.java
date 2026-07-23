package com.inforcol.cotizacion.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;
import com.inforcol.cotizacion.model.MarcaVehiculo;

@Mapper(componentModel = "spring")
public interface MarcaVehiculoMapper {

    // Convierte Entity -> Response DTO
    @Mapping(source = "id_marca", target = "id_marca_vehiculo")
    @Mapping(source = "nombre_marca", target = "nombre_marca_vehiculo")
    @Mapping(source = "pais_origen", target = "pais_origen_vehiculo")
    @Mapping(source = "abreviatura", target = "abreviatura_vehiculo")
    MarcaVehiculoResponseDto toDto(MarcaVehiculo marcaVehiculo);
 
    // Convierte Request DTO -> Entity
    @Mapping(source = "id_marca_vehiculo", target = "id_marca", ignore = true)
    @Mapping(source = "nombre_marca_vehiculo", target = "nombre_marca")
    @Mapping(source = "pais_origen_vehiculo", target = "pais_origen")
    @Mapping(source = "abreviatura_vehiculo", target = "abreviatura")
    MarcaVehiculo toEntity(MarcaVehiculoRequestDto dto);
 
    // Actualiza una entidad existente con los datos del DTO
    @Mapping(source = "id_marca_vehiculo", target = "id_marca", ignore = true)
    @Mapping(source = "nombre_marca_vehiculo", target = "nombre_marca")
    @Mapping(source = "pais_origen_vehiculo", target = "pais_origen")
    @Mapping(source = "abreviatura_vehiculo", target = "abreviatura")
    void updateEntityFromDto(MarcaVehiculoRequestDto dto, @MappingTarget MarcaVehiculo marcaVehiculo);

}
