package com.inforcol.cotizacion.service.estadoCotizacionService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.estadoCotizacionDTO.EstadoCotizacionDTO;
import com.inforcol.cotizacion.model.estadoCotizacion.EstadoCotizacion;
import com.inforcol.cotizacion.repository.estadoCotizacionRepository.EstadoCotizacionRepository;

@Service
public class EstadoCotizacionService {

    private final EstadoCotizacionRepository repository;

    public EstadoCotizacionService(EstadoCotizacionRepository repository) {
        this.repository = repository;
    }

    public List<EstadoCotizacionDTO> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public EstadoCotizacionDTO obtenerPorId(Long id) {
        EstadoCotizacion entidad = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de cotización no encontrado con ID: " + id));
        return convertirADto(entidad);
    }

    public EstadoCotizacionDTO guardar(EstadoCotizacionDTO dto) {
        EstadoCotizacion entidad = convertirAEntidad(dto);
        EstadoCotizacion guardado = repository.save(entidad);
        return convertirADto(guardado);
    }

    public EstadoCotizacionDTO actualizar(Long id, EstadoCotizacionDTO dto) {
        EstadoCotizacion entidadExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estado de cotización no encontrado con ID: " + id));

        entidadExistente.setDescripcion(dto.getDescripcion());
        entidadExistente.setEstado(dto.getEstado());
        entidadExistente.setFecInicio(dto.getFecInicio());
        entidadExistente.setFecFin(dto.getFecFin());

        EstadoCotizacion actualizado = repository.save(entidadExistente);
        return convertirADto(actualizado);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("No se encontró el estado de cotización con ID: " + id);
        }
        repository.deleteById(id);
    }

    // Mapeos manuales (puedes reemplazar esto con MapStruct o ModelMapper si prefieres)
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
