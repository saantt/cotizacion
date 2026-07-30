package com.inforcol.cotizacion.dto.deducibleDTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(
        name = "DeducibleResponseDTO",
        description = "Información de un deducible registrada en el sistema."
)
public class DeducibleResponseDTO {

    @Schema(
            description = "Identificador único del deducible.",
            example = "1"
    )
    private Long id_deducible;

    @Schema(
            description = "Porcentaje del deducible.",
            example = "10"
    )
    private int porcentaje;

    @Schema(
            description = "Monto mínimo del deducible.",
            example = "1500000"
    )
    private Double monto_minimo;

}