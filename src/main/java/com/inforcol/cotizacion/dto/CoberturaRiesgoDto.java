package com.inforcol.cotizacion.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data

public class CoberturaRiesgoDto {
    private String idCotizacion;
    private String idCobertura;
    private Integer idDeducible;
    private BigDecimal primaCobertura;
}
