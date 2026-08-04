package com.inforcol.cotizacion.mapper;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoRequestDto;
import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoResponseDto;
import com.inforcol.cotizacion.model.DatosRiesgo;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DatosRiesgoMapper {

    @Mapping(source = "idCotizacion", target = "id")
    @Mapping(source = "placa", target = "matricula")
    @Mapping(source = "ccTomador", target = "cedula")
    @Mapping(source = "idEstado", target = "estadoId")
    @Mapping(source = "fechaCotizacion", target = "fecha")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "tipoServicio", target = "servicio")
    @Mapping(source = "idMarca", target = "marcaId")
    DatosRiesgoResponseDto modeloAResponseDto(DatosRiesgo modelo);

    @Mapping(source = "id", target = "idCotizacion")
    @Mapping(source = "matricula", target = "placa")
    @Mapping(source = "cedula", target = "ccTomador")
    @Mapping(source = "estadoId", target = "idEstado")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "servicio", target = "tipoServicio")
    @Mapping(source = "marcaId", target = "idMarca")
    DatosRiesgo dtoAEntidad(DatosRiesgoRequestDto dto);

    @InheritConfiguration(name = "dtoAEntidad")
    @Mapping(target = "idCotizacion", ignore = true)
    void actualizarEntidad(DatosRiesgoRequestDto dto, @MappingTarget DatosRiesgo entidad);
}
