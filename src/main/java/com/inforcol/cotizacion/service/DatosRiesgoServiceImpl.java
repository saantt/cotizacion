package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoRequestDto;
import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoResponseDto;
import com.inforcol.cotizacion.mapper.DatosRiesgoMapper;
import com.inforcol.cotizacion.model.DatosRiesgo;
import com.inforcol.cotizacion.repository.DatosRiesgoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DatosRiesgoServiceImpl implements DatosRiesgoService {

    private final DatosRiesgoRepository repository;
    private final DatosRiesgoMapper mapper;

    @Override
    public List<DatosRiesgoResponseDto> obtenerTodos() {

        log.info("DatosRiesgoService.obtenerTodos() - Listando todos los datos de riesgo");

        List<DatosRiesgo> entidades = repository.findAll();
        
        return entidades.stream()
                .map(mapper::modeloAResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DatosRiesgoResponseDto obtenerPorPlaca(String placa) {

        log.info("DatosRiesgoService.obtenerPorPlaca() - Buscando dato de riesgo por placa: {}", placa);

        List<DatosRiesgo> entidades = repository.findByPlaca(placa);
        
        if (entidades.isEmpty()) {
            throw new RuntimeException("No se encontró el riesgo con placa: " + placa);
        }

        return mapper.modeloAResponseDto(entidades.get(0));
    }

    @Override
    public DatosRiesgoResponseDto guardar(DatosRiesgoRequestDto dto) {

        log.info("DatosRiesgoService.guardar() - Guardando nuevo dato de riesgo: {}", dto);
        
        DatosRiesgo entidad = mapper.dtoAEntidad(dto);

        return mapper.modeloAResponseDto(repository.save(entidad));
    }

    @Override
    public DatosRiesgoResponseDto actualizar(String id, DatosRiesgoRequestDto dto) {

        log.info("DatosRiesgoService.actualizar() - Actualizando dato de riesgo con ID: {}", id);

        DatosRiesgo entidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el riesgo con id: " + id));

        mapper.actualizarEntidad(dto, entidad);

        DatosRiesgo actualizado = repository.save(entidad);

        return mapper.modeloAResponseDto(actualizado);
    }

    @Override
    public DatosRiesgoResponseDto eliminar(String id) {

        log.info("DatosRiesgoService.eliminar() - Eliminando dato de riesgo con ID: {}", id);

        DatosRiesgo entidad = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el riesgo con id: " + id));

        DatosRiesgoResponseDto dto = mapper.modeloAResponseDto(entidad);

        repository.delete(entidad);

        return dto;
    }

    @Override
    public Page<DatosRiesgoResponseDto> obtenerTodosPaginado(Pageable page){
        
        log.info("DatosRiesgoService.obtenerTodosPaginado() - Listando todos los datos de riesgo paginados");

        return repository.findAll(page).map(mapper::modeloAResponseDto);
    }
}
