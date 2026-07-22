package com.inforcol.cotizacion.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "DATOS_DEL_RIESGO")
@Data
@NoArgsConstructor
public class DatosRiesgo {

    @Id
    @Column(name = "id_cotizacion", length = 50)
    private String idCotizacion;

    @Column(name = "placa", nullable = false, length = 10)
    private String placa;

    @Column(name = "cc_tomador", nullable = false, length = 20)
    private String ccTomador;

    @Column(name = "id_estado", nullable = false)
    private Integer idEstado;

    @Column(name = "fecha_cotizacion", nullable = false)
    private LocalDateTime fechaCotizacion = LocalDateTime.now();

    @Column(name = "modelo", nullable = false)
    private Integer modelo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servicio", length = 20)
    private TipoServicio tipoServicio;

    public enum TipoServicio {
        PUBLICO,
        PARTICULAR
    }
}
