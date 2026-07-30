package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.estadoCotizacionDTO.EstadoCotizacionDTO;
import com.inforcol.cotizacion.model.EstadoCotizacion;
import com.inforcol.cotizacion.repository.EstadoCotizacionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoCotizacionServiceImp implements EstadoCotizacionService {

    private static final Logger log = LoggerFactory.getLogger(EstadoCotizacionServiceImp.class);

    private final EstadoCotizacionRepository repository;

    public EstadoCotizacionServiceImp(EstadoCotizacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EstadoCotizacionDTO> obtenerTodos() {
        log.info("Iniciando consulta de todos los estados de cotización");
        
        List<EstadoCotizacionDTO> resultados = repository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());

        log.info("Consulta finalizada. Se encontraron {} registros", resultados.size());
        return resultados;
    }

    @Override
    public EstadoCotizacionDTO obtenerPorId(Long id) {
        log.info("Buscando estado de cotización con ID: {}", id);

        EstadoCotizacion entidad = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error: Estado de cotización no encontrado con ID: {}", id);
                    return new RuntimeException("Estado de cotización no encontrado con ID: " + id);
                });

        log.debug("Estado de cotización encontrado correctamente con ID: {}", id);
        return convertirADto(entidad);
    }

    @Override
    public EstadoCotizacionDTO guardar(EstadoCotizacionDTO dto) {
        log.info("Iniciando el proceso para guardar un nuevo estado de cotización");
        log.debug("Datos recibidos para guardar: {}", dto.getDescripcion());

        EstadoCotizacion entidad = convertirAEntidad(dto);
        EstadoCotizacion guardado = repository.save(entidad);

        log.info("Estado de cotización guardado exitosamente con ID asignado: {}", guardado.getIdEstado());
        return convertirADto(guardado);
    }

    @Override
    public EstadoCotizacionDTO actualizar(Long id, EstadoCotizacionDTO dto) {
        log.info("Iniciando actualización para el estado de cotización con ID: {}", id);

        EstadoCotizacion entidadExistente = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Error de actualización: No se encontró el estado de cotización con ID: {}", id);
                    return new RuntimeException("Estado de cotización no encontrado con ID: " + id);
                });

        entidadExistente.setDescripcion(dto.getDescripcion());
        entidadExistente.setEstado(dto.getEstado());
        entidadExistente.setFecInicio(dto.getFecInicio());
        entidadExistente.setFecFin(dto.getFecFin());

        EstadoCotizacion actualizado = repository.save(entidadExistente);
        log.info("Estado de cotización con ID: {} actualizado exitosamente", actualizado.getIdEstado());

        return convertirADto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        log.info("Iniciando solicitud para eliminar el estado de cotización con ID: {}", id);

        if (!repository.existsById(id)) {
            log.error("Error al eliminar: No se encontró el estado de cotización con ID: {}", id);
            throw new RuntimeException("No se encontró el estado de cotización con ID: " + id);
        }

        repository.deleteById(id);
        log.info("Estado de cotización con ID: {} eliminado correctamente", id);
    }

    // Mapeos manuales internos
    private EstadoCotizacionDTO convertirADto(EstadoCotizacion entidad) {
        EstadoCotizacionDTO dto = new EstadoCotizacionDTO();
        dto.setIdEstado(entidad.getIdEstado());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setEstado(entidad.getEstado());
        dto.setFecInicio(entidad.getFecInicio());
        dto.setFecFin(entidad.getFecFin());
        return dto;
    }

    private EstadoCotizacion convertirAEntidad(EstadoCotizacionDTO dto) {
        EstadoCotizacion entidad = new EstadoCotizacion();
        entidad.setIdEstado(dto.getIdEstado());
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setEstado(dto.getEstado());
        entidad.setFecInicio(dto.getFecInicio());
        entidad.setFecFin(dto.getFecFin());
        return entidad;
    }
}