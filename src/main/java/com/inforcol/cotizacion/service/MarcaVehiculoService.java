package com.inforcol.cotizacion.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.repository.MarcaVehiculoRepository;

@Service
public class MarcaVehiculoService {

    @Autowired
    private MarcaVehiculoRepository marcaVehiculoRepository;



}
