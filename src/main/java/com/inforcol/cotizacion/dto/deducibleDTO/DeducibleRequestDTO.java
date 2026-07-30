package com.inforcol.cotizacion.dto.deducibleDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(
        name = "DeducibleRequestDTO",
        description = "Objeto utilizado para crear o actualizar un deducible."
)
public class DeducibleRequestDTO {

    @Schema(
            description = "Porcentaje del deducible.",
            example = "10",
            minimum = "0",
            maximum = "100"
    )
    @NotNull(message = "El porcentaje es obligatorio")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    private Integer porcentaje;

    @Schema(
            description = "Monto mínimo que debe asumir el asegurado.",
            example = "1500000"
    )
    @NotNull(message = "El monto mínimo es obligatorio")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "El monto mínimo debe ser mayor que cero"
    )
    private Double monto_minimo;

}