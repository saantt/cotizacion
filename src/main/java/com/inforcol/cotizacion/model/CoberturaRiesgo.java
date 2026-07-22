package com.inforcol.cotizacion.model;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Entity
@Table(name = "COBERTURAS_DEL_RIESGO")
@Data
@NoArgsConstructor
@IdClass(CoberturaRiesgoId.class)
public class CoberturaRiesgo {

    @Id
    @Column(name = "id_cotizacion", length = 50)
    private String idCotizacion;

    @Id
    @Column(name = "id_cobertura", length = 20)
    private String idCobertura;

    @Column(name = "id_deducible")
    private Integer idDeducible;

    @Column(name = "prima_cobertura", nullable = false)
    private BigDecimal primaCobertura;
}