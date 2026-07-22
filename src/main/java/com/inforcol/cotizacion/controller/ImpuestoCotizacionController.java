package com.inforcol.cotizacion.controller;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionResponse;
import com.inforcol.cotizacion.service.ImpuestoCotizacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/impuestos-cotizacion")
public class ImpuestoCotizacionController {

    private final ImpuestoCotizacionService service;

    public ImpuestoCotizacionController(ImpuestoCotizacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ImpuestoCotizacionResponse> crear(
            @Valid @RequestBody ImpuestoCotizacionRequest request) {

        ImpuestoCotizacionResponse respuesta = service.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ImpuestoCotizacionResponse>> listarTodos() {

        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> actualizar(
            @PathVariable Integer id,
            @Valid @RequestBody ImpuestoCotizacionRequest request) {

        return ResponseEntity.ok(service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}