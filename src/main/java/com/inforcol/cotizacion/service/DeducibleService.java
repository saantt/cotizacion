package com.inforcol.cotizacion.service;



import java.util.List;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DeducibleService {

    List<DeducibleResponseDTO> getAllDeducibles();

    DeducibleResponseDTO findByDeducibleId(Long id);

    DeducibleResponseDTO createDeducible(DeducibleRequestDTO request);

    DeducibleResponseDTO updateDeducible(Long id,DeducibleRequestDTO request);

    void deleteDeducible(Long id);

    Page<DeducibleResponseDTO> getAllDedudiblesPage(Pageable page);

}