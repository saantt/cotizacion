package com.inforcol.cotizacion.dto.coverage;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CoverageRequestDto {

    @NotBlank(message = "Coverage id_cobertura is required")
    private String id_cobertura;

    @NotBlank(message = "Coverage nombre_cobertura is required")
    private String nombre_cobertura;

    @NotNull(message = "Coverage tasa_publico is required")
    @Positive(message = "Public tasa_publico must be greater than zero")
    private BigDecimal tasa_publico;

    @NotNull(message = "Coverage tasa_particular is required")
    @Positive(message = "Public tasa_particular must be greater than zero")
    private BigDecimal tasa_particular;
}
