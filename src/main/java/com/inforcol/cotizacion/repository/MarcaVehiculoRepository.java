package com.inforcol.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.MarcaVehiculo;

@Repository
public interface MarcaVehiculoRepository extends JpaRepository<MarcaVehiculo, Long>{

}
