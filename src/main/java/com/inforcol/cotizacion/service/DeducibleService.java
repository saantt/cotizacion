package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;

import java.util.List;

public interface DeducibleService {

    List<DeducibleResponseDTO> listar();

    DeducibleResponseDTO buscar(Long id);

    DeducibleResponseDTO guardar(DeducibleRequestDTO request);

    DeducibleResponseDTO actualizar(Long id,DeducibleRequestDTO request);

    void eliminar(Long id);

}