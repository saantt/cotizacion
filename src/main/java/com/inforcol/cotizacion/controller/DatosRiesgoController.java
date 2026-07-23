package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoDTO;
import com.inforcol.cotizacion.service.DatosRiesgoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/datos-riesgo")
public class DatosRiesgoController {

    @Autowired
    private DatosRiesgoService service;

    @GetMapping
    public List<DatosRiesgoDTO> listarTodos() {
        return service.obtenerTodos();
    }

    @PostMapping
    public DatosRiesgoDTO crear(@Valid @RequestBody DatosRiesgoDTO dto) {
        return service.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRiesgoDTO> actualizar(
            @PathVariable String id,
            @Valid @RequestBody DatosRiesgoDTO dto
    ) {

        DatosRiesgoDTO respuesta = service.actualizar(id, dto);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public DatosRiesgoDTO eliminar(@PathVariable String id) {
        return service.eliminar(id);
    }

}
