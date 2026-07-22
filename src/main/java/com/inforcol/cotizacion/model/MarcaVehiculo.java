package com.inforcol.cotizacion.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class MarcaVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_marca;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String nombre_marca;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String pais_origen;

    @NotBlank(message = "El campo no puede ser nulo ni vacío.")
    private String abreviatura;

}
