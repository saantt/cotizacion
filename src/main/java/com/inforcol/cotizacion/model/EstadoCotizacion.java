package com.inforcol.cotizacion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "estados_cotizacion")
@Data
public class EstadoCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long idEstado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "fec_inicio")
    private LocalDateTime fecInicio;

    @Column(name = "fec_fin")
    private LocalDateTime fecFin;
}
