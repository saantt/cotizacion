package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;

public interface MarcaVehiculoService {

    MarcaVehiculoResponseDto crearMarcaVehiculo(MarcaVehiculoRequestDto request);

    List<MarcaVehiculoResponseDto> obtenerTodasMarcaVehiculo();

    MarcaVehiculoResponseDto obtenerMarcaVehiculoPorId(Long id);

    MarcaVehiculoResponseDto actualizarMarcaVehiculo(Long id, MarcaVehiculoRequestDto request);

    void eliminarMarcaVehiculo(Long id);

    MarcaVehiculoResponseDto obtenerMarcaPorNombre(String nombre);

    List<MarcaVehiculoResponseDto> obtenerMarcasPorPais(String pais);

    String validarExistenciaMarca(String nombreMarca);

    MarcaVehiculoResponseDto obtenerMarcaPorAbreviatura(String abreviatura);

    Page<MarcaVehiculoResponseDto> obtenerTodasMarcaVehiculoPagina(Pageable pageable);

}
