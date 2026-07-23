package com.inforcol.cotizacion.dto.coverage;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CoverageResponseDto {
    private String id_cobertura;
    private String nombre_cobertura;
    private BigDecimal tasa_publico;
    private BigDecimal tasa_particular;
}
