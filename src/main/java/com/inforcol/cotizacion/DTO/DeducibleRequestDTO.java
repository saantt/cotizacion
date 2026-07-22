package com.inforcol.cotizacion.dto.deducibleDTO;

import lombok.Data;

@Data
public class DeducibleRequestDTO {

    private int porcentaje;
    private Double monto_minimo;

}