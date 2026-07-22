package com.inforcol.cotizacion.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CoberturaRiesgoResponseDto {
    private String idCotizacion;
    private String idCobertura;
    private Integer idDeducible;
    private BigDecimal primaCobertura;
}
