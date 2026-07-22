package com.inforcol.cotizacion.repository.estadoCotizacionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.estadoCotizacion.EstadoCotizacion;

@Repository
public interface EstadoCotizacionRepository extends JpaRepository<EstadoCotizacion, Long> {

}
