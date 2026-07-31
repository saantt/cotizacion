package com.inforcol.cotizacion.repository.ImpuestoCotizacionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.ImpuestoCotizacion.ImpuestoCotizacion;

@Repository
public interface ImpuestoCotizacionRepository extends JpaRepository<ImpuestoCotizacion, Integer> {
}