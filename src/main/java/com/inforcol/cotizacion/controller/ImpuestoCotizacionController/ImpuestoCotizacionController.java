package com.inforcol.cotizacion.controller.ImpuestoCotizacionController;

import java.util.List;

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

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;
import com.inforcol.cotizacion.service.ImpuestoCotizacionService.ImpuestoCotizacionService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@Validated
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
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> actualizar(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id,

            @Valid
            @RequestBody
            ImpuestoCotizacionRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}