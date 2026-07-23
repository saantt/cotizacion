package com.inforcol.cotizacion.mapper;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
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
    DatosRiesgoDTO modeloADto(DatosRiesgo modelo);

    @Mapping(source = "id", target = "idCotizacion")
    @Mapping(source = "matricula", target = "placa")
    @Mapping(source = "cedula", target = "ccTomador")
    @Mapping(source = "estadoId", target = "idEstado")
    @Mapping(source = "fecha", target = "fechaCotizacion")
    @Mapping(source = "modelo", target = "modelo")
    @Mapping(source = "servicio", target = "tipoServicio")
    DatosRiesgo dtoAModelo(DatosRiesgoDTO dto);

    @InheritConfiguration(name = "dtoAModelo")
    @Mapping(target = "idCotizacion", ignore = true)
    void actualizarEntidad(DatosRiesgoDTO dto, @MappingTarget DatosRiesgo entidad);
}
