package com.inforcol.cotizacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "marcas_vehiculo") 
@Data
public class MarcaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca", nullable = false)
    private Long idMarca;

    @Column(name = "nombre_marca", nullable = false)
    private String nombreMarca;

    @Column(name = "pais_origen", nullable = false)
    private String paisOrigen;

    @Column(name = "abreviatura", nullable = false)
    private String abreviatura;

}
