package com.inforcol.cotizacion.dto.datos_riesgo;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(name = "DatosRiesgoDTO", description = "DTO que representa los datos del riesgo para una cotización")
public class DatosRiesgoDTO {

    @NotBlank(message = "El ID de cotización es obligatorio")
    @Size(max = 50)
    @Schema(description = "Identificador de la cotización", example = "COT-2026-0001")
    private String id;

    @NotBlank(message = "La placa es obligatoria")
    @Size(max = 10)
    @Schema(description = "Matrícula o placa del vehículo", example = "ABC123")
    private String matricula;

    @NotBlank(message = "La cédula del tomador es obligatoria")
    @Size(max = 20)
    @Schema(description = "Documento de identidad del tomador", example = "1234567890")
    private String cedula;

    @NotNull(message = "El ID de estado es obligatorio") 
    @Schema(description = "Identificador del estado de la cotización", example = "1")
    private Integer estadoId;

    @Schema(description = "Fecha de creación/actualización de la cotización", example = "2026-07-30T12:34:56")
    private LocalDateTime fecha;

    @NotNull(message = "El modelo es obligatorio") 
    @Schema(description = "Año o código del modelo del vehículo", example = "2020")
    private Integer modelo;

    @NotNull(message = "El tipo de servicio es obligatorio") 
    @Schema(description = "Tipo de servicio del seguro (e.g., particular, taxi)", example = "PARTICULAR")
    private TipoServicio servicio; 
}
