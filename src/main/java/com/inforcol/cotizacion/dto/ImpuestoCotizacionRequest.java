package com.inforcol.cotizacion.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ImpuestoCotizacionRequest {

    @NotBlank(message = "El id de la cotización es obligatorio")
    @Size(max = 50, message = "El id de la cotización no puede superar los 50 caracteres")
    private String idCotizacion;

    @NotBlank(message = "El concepto es obligatorio")
    @Size(max = 50, message = "El concepto no puede superar los 50 caracteres")
    private String concepto;

    @NotNull(message = "El valor es obligatorio")
    @DecimalMin(value = "0.00", inclusive = true,
            message = "El valor no puede ser negativo")
    private BigDecimal valor;
}