package com.inforcol.cotizacion.dto.CoberturaRiesgoDto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data

public class CoberturaRiesgoRequestDto {
    @NotBlank(message = "El idCotizacion no puede estar vacío")
    @Size(max = 20, message = "El idCotizacion no puede tener más de 20 caracteres")
    private String idCotizacion;

    @NotBlank(message = "El idCobertura no puede estar vacío")
    @Size(max = 20, message = "El idCobertura no puede tener más de 6 caracteres")
    private String idCobertura;

    @Positive(message = "El idDeducible debe ser un número positivo")
    private Integer idDeducible;

    @NotNull(message = "La prima de cobertura no puede ser nula")
    @Digits(integer = 10, fraction = 2)
    @Positive
    private BigDecimal primaCobertura;
}
