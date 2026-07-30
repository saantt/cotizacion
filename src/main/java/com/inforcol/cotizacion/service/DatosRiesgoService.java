package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoDTO;

public interface DatosRiesgoService {

    List<DatosRiesgoDTO> obtenerTodos();

    DatosRiesgoDTO obtenerPorPlaca(String placa);

    DatosRiesgoDTO guardar(DatosRiesgoDTO dto);

    DatosRiesgoDTO actualizar(String id, DatosRiesgoDTO dto);

    DatosRiesgoDTO eliminar(String id);

}
