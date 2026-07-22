package com.inforcol.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.CoverageModel;

@Repository
public interface CoverageRepository extends JpaRepository<CoverageModel, String> {

}
