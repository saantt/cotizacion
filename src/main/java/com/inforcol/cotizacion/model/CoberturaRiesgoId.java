package com.inforcol.cotizacion.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoberturaRiesgoId implements Serializable {
    private String idCotizacion;
    private String idCobertura;
}