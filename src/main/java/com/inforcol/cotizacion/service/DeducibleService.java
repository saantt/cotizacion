package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;

import java.util.List;

public interface DeducibleService {

    List<DeducibleResponseDTO> getAllDeducibles();

    DeducibleResponseDTO findByDeducibleId(Long id);

    DeducibleResponseDTO createDeducible(DeducibleRequestDTO request);

    DeducibleResponseDTO updateDeducible(Long id,DeducibleRequestDTO request);

    void deleteDeducible(Long id);

}