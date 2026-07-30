package com.inforcol.cotizacion.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.estadoCotizacionDTO.EstadoCotizacionDTO;
import com.inforcol.cotizacion.service.EstadoCotizacionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/estados-cotizacion")
@Validated
public class EstadoCotizacionController {

    private static final Logger log = LoggerFactory.getLogger(EstadoCotizacionController.class);

    private final EstadoCotizacionService service;

    public EstadoCotizacionController(EstadoCotizacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoCotizacionDTO>> obtenerTodos() {
        log.info("REST request para obtener todos los estados de cotización");
        
        List<EstadoCotizacionDTO> lista = service.obtenerTodos();
        
        log.debug("Respondiendo petición GET /api/estados-cotizacion con {} elementos", lista.size());
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCotizacionDTO> obtenerPorId(
            @PathVariable @NotNull @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        
        log.info("REST request para obtener estado de cotización con ID: {}", id);
        
        EstadoCotizacionDTO dto = service.obtenerPorId(id);
        
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EstadoCotizacionDTO> crear(@Valid @RequestBody EstadoCotizacionDTO dto) {
        log.info("REST request para crear un nuevo estado de cotización");
        log.debug("Payload recibido para crear: {}", dto.getDescripcion());

        EstadoCotizacionDTO nuevoEstado = service.guardar(dto);
        
        log.info("Estado de cotización creado exitosamente con ID: {}", nuevoEstado.getIdEstado());
        return new ResponseEntity<>(nuevoEstado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCotizacionDTO> actualizar(
            @PathVariable @NotNull @Min(value = 1, message = "El ID debe ser mayor a 0") Long id, 
            @Valid @RequestBody EstadoCotizacionDTO dto) {
        
        log.info("REST request para actualizar estado de cotización con ID: {}", id);
        log.debug("Payload recibido para actualización del ID {}: {}", id, dto.getDescripcion());

        EstadoCotizacionDTO estadoActualizado = service.actualizar(id, dto);
        
        log.info("Estado de cotización con ID: {} actualizado correctamente", id);
        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @NotNull @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        
        log.info("REST request para eliminar estado de cotización con ID: {}", id);

        service.eliminar(id);
        
        log.info("Estado de cotización con ID: {} eliminado exitosamente via endpoint REST", id);
        return ResponseEntity.noContent().build();
    }
}