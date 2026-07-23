package com.inforcol.cotizacion.DTO.deducibleDTO;

import lombok.Data;

@Data
public class DeducibleRequestDTO {

    private int porcentaje;
    private Double monto_minimo;

}