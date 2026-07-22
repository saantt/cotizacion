package com.inforcol.cotizacion.dto.deducibleDTO;

import lombok.Data;

@Data
public class DeducibleResponseDTO {

    private Long id_deducible;
    private int porcentaje;
    private Double monto_minimo;

}