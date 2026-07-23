package com.inforcol.cotizacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "deducibles")
@Data
public class Deducible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_deducible;
    private int porcentaje;
    private Double monto_minimo;

}
