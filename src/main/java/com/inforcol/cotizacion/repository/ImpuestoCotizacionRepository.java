package com.inforcol.cotizacion.repository;

import com.inforcol.cotizacion.model.ImpuestoCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpuestoCotizacionRepository extends JpaRepository<ImpuestoCotizacion, Integer> {
}