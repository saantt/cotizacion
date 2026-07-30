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
import lombok.extern.slf4j.Slf4j;

@RestController
@Validated
@RequestMapping("/api/impuestos-cotizacion")
@Slf4j
public class ImpuestoCotizacionController {

    private final ImpuestoCotizacionService service;

    public ImpuestoCotizacionController(ImpuestoCotizacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ImpuestoCotizacionResponse> crear(
            @Valid @RequestBody ImpuestoCotizacionRequest request) {

        log.info("Solicitud para crear impuesto de cotizacion");
        ImpuestoCotizacionResponse respuesta = service.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @GetMapping
    public ResponseEntity<List<ImpuestoCotizacionResponse>> listarTodos() {

        log.info("Solicitud para listar todos los impuestos de cotizacion");
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> buscarPorId(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        log.info("Solicitud para buscar impuesto de cotizacion con id: {}", id);
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

        log.info("Solicitud para actualizar impuesto de cotizacion con id: {}", id);
        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        log.info("Solicitud para eliminar impuesto de cotizacion con id: {}", id);
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
