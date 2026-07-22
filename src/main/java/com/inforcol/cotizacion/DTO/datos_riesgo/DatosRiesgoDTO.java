package com.inforcol.cotizacion.DTO.datos_riesgo;

import java.time.LocalDateTime;

import com.inforcol.cotizacion.model.DatosRiesgo.TipoServicio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosRiesgoDTO {

    @NotBlank(message = "El ID de cotización es obligatorio")
    @Size(max = 50)
    private String idCotizacion;

    @NotBlank(message = "La placa es obligatoria")
    @Size(max = 10)
    private String placa;

    @NotBlank(message = "La cédula del tomador es obligatoria")
    @Size(max = 20)
    private String ccTomador;

    @NotNull(message = "El ID de estado es obligatorio")
    private Integer idEstado;

    private LocalDateTime fechaCotizacion;

    @NotNull(message = "El modelo es obligatorio")
    private Integer modelo;

    @NotNull(message = "El tipo de servicio es obligatorio")
    private TipoServicio tipoServicio;
}
