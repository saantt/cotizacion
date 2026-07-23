package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
import com.inforcol.cotizacion.mapper.DatosRiesgoMapper;
import com.inforcol.cotizacion.model.DatosRiesgo;
import com.inforcol.cotizacion.repository.DatosRiesgoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DatosRiesgoService {

    private final DatosRiesgoRepository repository;
    private final DatosRiesgoMapper mapper;

    public List<DatosRiesgoDTO> obtenerTodos() {

        List<DatosRiesgo> entidades = repository.findAll();
        
        return entidades.stream()
                .map(mapper::modeloADto)
                .collect(Collectors.toList());
    }

    public DatosRiesgoDTO guardar(DatosRiesgoDTO dto) {
        
        DatosRiesgo entidad = mapper.dtoAModelo(dto);

        return mapper.modeloADto(repository.save(entidad));
    }

    public DatosRiesgoDTO actualizar(String id, DatosRiesgoDTO dto) {

        DatosRiesgo entidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el riesgo con id: " + id));

        mapper.actualizarEntidad(dto, entidad);

        DatosRiesgo actualizado = repository.save(entidad);

        return mapper.modeloADto(actualizado);
    }

    public DatosRiesgoDTO eliminar(String id) {
        DatosRiesgo entidad = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el riesgo con id: " + id));

        DatosRiesgoDTO dto = mapper.modeloADto(entidad);

        repository.delete(entidad);

        return dto;
    }
}
