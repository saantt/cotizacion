package com.inforcol.cotizacion.model.ImpuestoCotizacion;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "impuestos_cotizacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpuestoCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_impuesto_cot")
    private Integer idImpuestoCot;

    @Column(name = "id_cotizacion", nullable = false, length = 50)
    private String idCotizacion;

    @Column(name = "concepto", nullable = false, length = 50)
    private String concepto;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    private BigDecimal valor;
}