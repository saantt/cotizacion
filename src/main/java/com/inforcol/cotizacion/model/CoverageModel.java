package com.inforcol.cotizacion.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data //Para no crear Getters y Setters
@Entity
@Table(name = "COBERTURAS")
public class CoverageModel {

    @Id
    private String id_cobertura;

    private String nombre_cobertura;

    private BigDecimal tasa_publico;

    private BigDecimal tasa_particular;

    /* @OneToMany(mappedBy = "coverage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<entidad-cobertura-riesgo> entidad-cobertura-riesgo */ ;
}
