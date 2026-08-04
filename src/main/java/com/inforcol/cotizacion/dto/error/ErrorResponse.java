package com.inforcol.cotizacion.dto.error;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "Fecha y hora del error")
    private LocalDateTime timestamp;

    @Schema(description = "Código de estado HTTP")
    private int status;

    @Schema(description = "Nombre del error HTTP")
    private String error;

    @Schema(description = "Mensaje general")
    private String mensaje;

    @Schema(description = "Ruta donde ocurrió el error")
    private String path;

    @Schema(description = "Errores de validación por campo")
    private Map<String, String> errores;

    public static ErrorResponse of(HttpStatus status, String mensaje, String path) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensaje, path, null);
    }

    public static ErrorResponse ofValidacion(HttpStatus status, String mensaje, String path, Map<String, String> errores) {
        return new ErrorResponse(LocalDateTime.now(), status.value(), status.getReasonPhrase(), mensaje, path, errores);
    }
}