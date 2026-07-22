package com.inforcol.cotizacion.dto.estadoCotizacionDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EstadoCotizacionDTO {

    private Long idEstado;

    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fecInicio;

    @NotNull(message = "La fecha fin es obligatoria")
    private LocalDateTime fecFin;
}
