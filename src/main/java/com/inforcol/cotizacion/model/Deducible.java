package com.inforcol.cotizacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Deducible {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id_deducible;
     private int porcentaje;
     private Double monto_minimo;

}
