package com.inforcol.cotizacion.mapper;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.model.Deducible;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeducibleMapper {

    Deducible toEntity(DeducibleRequestDTO request);

    DeducibleResponseDTO toResponseDTO(Deducible entity);

}
