package com.inforcol.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.cotizacion.model.EstadoCotizacion;

public interface EstadoCotizacionRepository extends JpaRepository<EstadoCotizacion, Long> {

}
