package com.inforcol.cotizacion.dto.marcavehiculo;

import lombok.Data;

@Data
public class MarcaVehiculoRequestDto {
    
    private Long id_marca_vehiculo;

    private String nombre_marca_vehiculo;

    private String pais_origen_vehiculo;

    private String abreviatura_vehiculo;

}
