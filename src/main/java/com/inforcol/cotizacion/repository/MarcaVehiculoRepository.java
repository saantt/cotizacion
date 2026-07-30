package com.inforcol.cotizacion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.MarcaVehiculo;

@Repository
public interface MarcaVehiculoRepository extends JpaRepository<MarcaVehiculo, Long>{

    // Buscar por nombre exacto
    MarcaVehiculo findByNombreMarca(String nombreMarca);

    // Buscar todas las marcas de un país
    List<MarcaVehiculo> findByPaisOrigen(String paisOrigen);

    // Validar si existe una marca
    boolean existsByNombreMarca(String nombreMarca);

    // Buscar por abreviatura
    MarcaVehiculo findByAbreviatura(String abreviatura);

}
