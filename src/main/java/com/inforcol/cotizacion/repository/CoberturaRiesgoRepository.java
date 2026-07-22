package com.inforcol.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inforcol.cotizacion.model.CoberturaRiesgo;
import com.inforcol.cotizacion.model.CoberturaRiesgoId;

public interface CoberturaRiesgoRepository extends JpaRepository<CoberturaRiesgo, CoberturaRiesgoId> {
}