package com.inforcol.cotizacion.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.CoverageModel;

@Repository
public interface CoverageRepository extends JpaRepository<CoverageModel, String> {
    @Query("SELECT c FROM CoverageModel c WHERE c.nombre_cobertura = :nombre")
    List<CoverageModel> findByNombreCobertura(@Param("nombre") String nombre);

    @Query("SELECT c FROM CoverageModel c WHERE c.tasa_publico >= :rate ORDER BY c.tasa_publico DESC")
    List<CoverageModel> findGreaterRate(@Param("rate") BigDecimal rate);
}
