package com.inforcol.cotizacion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.service.MarcaVehiculoService;

@RestController
@RequestMapping("/api/marcaVehiculo")
public class MarcaVehiculoController {

    @Autowired
    private MarcaVehiculoService marcaVehiculoService;

}
