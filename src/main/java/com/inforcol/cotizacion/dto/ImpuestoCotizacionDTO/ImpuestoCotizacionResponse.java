package com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpuestoCotizacionResponse {

    private Integer idImpuestoCot;
    private String idCotizacion;
    private String concepto;
    private BigDecimal valor;
}