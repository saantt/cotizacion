package com.inforcol.cotizacion.dto.datos_riesgo;

import com.inforcol.cotizacion.model.DatosRiesgo.TipoServicio;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de solicitud para crear o actualizar datos del riesgo")
public class DatosRiesgoRequestDto {

    @Size(min = 13, max = 13, message = "La matrícula debe tener 13 caracteres")
    @Pattern(
        regexp = "^COT-\\d{4}-\\d{4}$")
    @Schema(description = "Identificador de la cotización (opcional)", example = "COT-2026-0001")
    private String id;

    @NotBlank(message = "La placa es obligatoria")
    @Size(min = 7, max = 7, message = "La matrícula debe tener 7 caracteres")
    @Pattern(
        regexp = "^[A-Z]{3}-\\d{3}$",
        message = "La matrícula debe tener el formato UFO-924"
    )
    @Schema(description = "Matrícula o placa del vehículo", example = "UFO-924")
    private String matricula;

    @NotBlank(message = "La cédula del tomador es obligatoria")
    @Size(max = 20)
    @Schema(description = "Documento de identidad del tomador", example = "1234567890")
    private String cedula;

    @NotNull(message = "El ID de estado es obligatorio")
    @Schema(description = "Identificador del estado de la cotización", example = "1")
    private Integer estadoId;

    @NotNull(message = "El modelo es obligatorio")
    @Schema(description = "Año o código del modelo del vehículo", example = "2020")
    private Integer modelo;

    @NotNull(message = "El tipo de servicio es obligatorio")
    @Schema(description = "Tipo de servicio del seguro", example = "PARTICULAR")
    private TipoServicio servicio;

    @NotNull(message = "El ID de marca es obligatorio")
    @Schema(description = "Identificador de la marca del vehículo", example = "5")
    private Integer marcaId;
}
