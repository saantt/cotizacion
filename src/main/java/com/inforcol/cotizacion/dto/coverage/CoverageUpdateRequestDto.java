package com.inforcol.cotizacion.dto.coverage;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CoverageUpdateRequestDto {
    private String nombre_cobertura;
    private BigDecimal tasa_publico;
    private BigDecimal tasa_particular;
}
