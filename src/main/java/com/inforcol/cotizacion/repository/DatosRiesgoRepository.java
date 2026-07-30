package com.inforcol.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.cotizacion.model.DatosRiesgo;

public interface DatosRiesgoRepository extends JpaRepository<DatosRiesgo, String>{
    List<DatosRiesgo> findByPlaca(String placa);
}