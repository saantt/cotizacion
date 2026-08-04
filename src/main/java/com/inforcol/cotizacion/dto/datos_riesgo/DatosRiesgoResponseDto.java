package com.inforcol.cotizacion.dto.datos_riesgo;

import java.time.LocalDateTime;

import com.inforcol.cotizacion.model.DatosRiesgo.TipoServicio;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de respuesta para los datos del riesgo")
public class DatosRiesgoResponseDto {

    @Schema(description = "Identificador de la cotización", example = "COT-2026-0001")
    private String id;

    @Schema(description = "Matrícula o placa del vehículo", example = "ABC123")
    private String matricula;

    @Schema(description = "Documento de identidad del tomador", example = "1234567890")
    private String cedula;

    @Schema(description = "Identificador del estado de la cotización", example = "1")
    private Integer estadoId;

    @Schema(description = "Fecha de creación/actualización de la cotización", example = "2026-07-30T12:34:56")
    private LocalDateTime fecha;

    @Schema(description = "Año o código del modelo del vehículo", example = "2020")
    private Integer modelo;

    @Schema(description = "Tipo de servicio del seguro", example = "PARTICULAR")
    private TipoServicio servicio;

    @Schema(description = "Identificador de la marca del vehículo", example = "5")
    private Integer marcaId;
}
