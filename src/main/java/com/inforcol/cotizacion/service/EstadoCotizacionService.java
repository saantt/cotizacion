package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.estadoCotizacionDTO.EstadoCotizacionDTO;

import java.util.List;

public interface EstadoCotizacionService {

    List<EstadoCotizacionDTO> obtenerTodos();

    EstadoCotizacionDTO obtenerPorId(Long id);

    EstadoCotizacionDTO guardar(EstadoCotizacionDTO dto);

    EstadoCotizacionDTO actualizar(Long id, EstadoCotizacionDTO dto);

    void eliminar(Long id);
}
