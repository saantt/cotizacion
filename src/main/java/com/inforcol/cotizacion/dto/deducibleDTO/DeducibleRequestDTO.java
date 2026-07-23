package com.inforcol.cotizacion.dto.deducibleDTO;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeducibleRequestDTO {

     @NotNull(message = "El porcentaje es obligatorio")
    @Min(value = 0, message = "El porcentaje no puede ser menor a 0")
    @Max(value = 100, message = "El porcentaje no puede ser mayor a 100")
    private Integer porcentaje;

    @NotNull(message = "El monto mínimo es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "El monto mínimo debe ser mayor que cero")
    private Double monto_minimo;
}