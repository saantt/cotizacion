package com.inforcol.cotizacion.dto.CoberturaRiesgoDto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data

public class CoberturaRiesgoRequestDto {
    @NotBlank
    private String idCotizacion;

     @NotBlank
    private String idCobertura;

    private Integer idDeducible;

    @NotNull
    @Positive
    private BigDecimal primaCobertura;
}
