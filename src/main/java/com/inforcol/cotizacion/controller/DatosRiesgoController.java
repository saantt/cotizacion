package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
import com.inforcol.cotizacion.service.DatosRiesgoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/datos-riesgo")
public class DatosRiesgoController {

    @Autowired
    private DatosRiesgoService service;

    // 1. Endpoint para listar todos los datos
    @GetMapping
    public List<DatosRiesgoDTO> listarTodos() {
        return service.obtenerTodos();
    }

    // 2. Endpoint para crear un nuevo registro
    @PostMapping
    public String crear(@Valid @RequestBody DatosRiesgoDTO dto) {
        service.guardar(dto);
        return "Registro guardado exitosamente";
    }

}
