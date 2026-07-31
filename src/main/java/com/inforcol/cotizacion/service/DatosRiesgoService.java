package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoRequestDto;
import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoResponseDto;

public interface DatosRiesgoService {

    List<DatosRiesgoResponseDto> obtenerTodos();

    DatosRiesgoResponseDto obtenerPorPlaca(String placa);

    DatosRiesgoResponseDto guardar(DatosRiesgoRequestDto dto);

    DatosRiesgoResponseDto actualizar(String id, DatosRiesgoRequestDto dto);

    DatosRiesgoResponseDto eliminar(String id);

}
