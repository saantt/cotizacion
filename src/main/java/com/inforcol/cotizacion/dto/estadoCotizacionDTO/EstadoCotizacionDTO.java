package com.inforcol.cotizacion.dto.estadoCotizacionDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EstadoCotizacionDTO {

   private Long idEstado;

    @NotBlank(message = "La descripción no puede estar vacía ni ser nula")
    @Size(min = 3, max = 100, message = "La descripción debe tener entre 3 y 100 caracteres")
    private String descripcion;

    @NotBlank(message = "El estado es obligatorio")
    @Size(max = 20, message = "El estado no puede superar los 20 caracteres")
    private String estado;

    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDateTime fecInicio;

    private LocalDateTime fecFin;

    // Constructores
    public EstadoCotizacionDTO() {}

    // Getters y Setters
    public Long getIdEstado() { return idEstado; }
    public void setIdEstado(Long idEstado) { this.idEstado = idEstado; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFecInicio() { return fecInicio; }
    public void setFecInicio(LocalDateTime fecInicio) { this.fecInicio = fecInicio; }

    public LocalDateTime getFecFin() { return fecFin; }
    public void setFecFin(LocalDateTime fecFin) { this.fecFin = fecFin; }

}
