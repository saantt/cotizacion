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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/estados-cotizacion")
@Validated
@Tag(name = "Estado de Cotización", description = "Operaciones sobre estados de cotización")
public class EstadoCotizacionController {

    private static final Logger log = LoggerFactory.getLogger(EstadoCotizacionController.class);

    private final EstadoCotizacionService service;

    public EstadoCotizacionController(EstadoCotizacionService service) {
        this.service = service;
    }

    @Operation(
        summary = "Obtener todos los estados", 
        description = "Retorna la lista completa de estados de cotización registrados en el sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })

    @GetMapping
    public ResponseEntity<List<EstadoCotizacionDTO>> obtenerTodos() {
        log.info("REST request para obtener todos los estados de cotización");
        
        List<EstadoCotizacionDTO> lista = service.obtenerTodos();
        
        log.debug("Respondiendo petición GET /api/estados-cotizacion con {} elementos", lista.size());
        return ResponseEntity.ok(lista);
    }

    @Operation(
        summary = "Obtener estado por ID", 
        description = "Busca un estado de cotización específico mediante su ID numérico."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado encontrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "ID inválido proporcionado en la URL", content = @Content),
        @ApiResponse(responseCode = "404", description = "No existe un estado con el ID especificado", content = @Content)
    })

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCotizacionDTO> obtenerPorId(
            @PathVariable @NotNull @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        
        log.info("REST request para obtener estado de cotización con ID: {}", id);
        
        EstadoCotizacionDTO dto = service.obtenerPorId(id);
        
        return ResponseEntity.ok(dto);
    }

    @Operation(
        summary = "Crear nuevo estado", 
        description = "Registra un nuevo estado de cotización validando que los datos requeridos sean correctos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estado de cotización creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "El payload recibido contiene errores de validación", content = @Content)
    })

    @PostMapping
    public ResponseEntity<EstadoCotizacionDTO> crear(@Valid @RequestBody EstadoCotizacionDTO dto) {
        log.info("REST request para crear un nuevo estado de cotización");
        log.debug("Payload recibido para crear: {}", dto.getDescripcion());

        EstadoCotizacionDTO nuevoEstado = service.guardar(dto);
        
        log.info("Estado de cotización creado exitosamente con ID: {}", nuevoEstado.getIdEstado());
        return new ResponseEntity<>(nuevoEstado, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Actualizar estado existente", 
        description = "Actualiza los campos de un estado de cotización existente según el ID enviado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estado actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "ID o payload con datos no válidos", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se encontró el estado con el ID indicado", content = @Content)
    })

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

    @Operation(
        summary = "Eliminar estado por ID", 
        description = "Elimina un estado de cotización de la base de datos a partir de su ID."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Estado eliminado exitosamente (Sin Contenido)"),
        @ApiResponse(responseCode = "400", description = "ID con formato no válido", content = @Content),
        @ApiResponse(responseCode = "404", description = "No se encontró el estado con el ID indicado para eliminar", content = @Content)
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable @NotNull @Min(value = 1, message = "El ID debe ser mayor a 0") Long id) {
        
        log.info("REST request para eliminar estado de cotización con ID: {}", id);

        service.eliminar(id);
        
        log.info("Estado de cotización con ID: {} eliminado exitosamente via endpoint REST", id);
        return ResponseEntity.noContent().build();
    }
}