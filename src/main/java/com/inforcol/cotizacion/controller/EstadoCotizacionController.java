package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.estadoCotizacionDTO.EstadoCotizacionDTO;
import com.inforcol.cotizacion.service.estadoCotizacionService.EstadoCotizacionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estados-cotizacion")
public class EstadoCotizacionController {

private final EstadoCotizacionService service;

    public EstadoCotizacionController(EstadoCotizacionService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<EstadoCotizacionDTO>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCotizacionDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<EstadoCotizacionDTO> crear(@Valid @RequestBody EstadoCotizacionDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoCotizacionDTO> actualizar(@PathVariable Long id, @Valid @RequestBody EstadoCotizacionDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
