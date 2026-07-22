package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
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
    public String crear(@Valid @RequestBody DatosRiesgoDTO dto) {
        service.guardar(dto);
        return "Registro guardado exitosamente";
    }

}
