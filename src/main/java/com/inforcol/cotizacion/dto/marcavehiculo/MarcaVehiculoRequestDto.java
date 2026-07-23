package com.inforcol.cotizacion.dto.marcavehiculo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MarcaVehiculoRequestDto {
    
    private Long id_marca_vehiculo;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String nombre_marca_vehiculo;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String pais_origen_vehiculo;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String abreviatura_vehiculo;

}
