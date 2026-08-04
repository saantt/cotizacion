package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.dto.error.ErrorResponse;
import com.inforcol.cotizacion.service.CoberturaRiesgoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Tag(
    name = "Cobertura Riesgo",
    description = "API para la gestión de coberturas de riesgo de las cotizaciones"
)

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/coberturariesgo")
@Slf4j
public class CoberturaRiesgoController {

    private final CoberturaRiesgoService service;

    public CoberturaRiesgoController(CoberturaRiesgoService service) {
        this.service = service;
    }

    @Operation(
        summary = "Obtener todas las coberturas de riesgo",
        description = "Retorna la lista completa de coberturas de riesgo registradas."
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Listado obtenido correctamente",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = CoberturaRiesgoResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
            )
    })

    @GetMapping
    public ResponseEntity<List<CoberturaRiesgoResponseDto>> getAllCoberturaRiesgo() {
        log.info("CoberturaRiesgoController -> listar()");
        return ResponseEntity.ok(service.getAllCoberturaRiesgo());
    }

    @Operation(
    summary = "Buscar una cobertura de riesgo",
    description = "Obtiene una cobertura de riesgo mediante el id de la cotización y el id de la cobertura."
)
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Cobertura encontrada",
                content = @Content(
                        schema = @Schema(implementation = CoberturaRiesgoResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Cobertura no encontrada",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @GetMapping("/{idCotizacion}/{idCobertura}")
    public ResponseEntity<CoberturaRiesgoResponseDto> findByCoberturaRiesgoId(@PathVariable String idCotizacion,
                                                               @PathVariable String idCobertura) {
        log.info("CoberturaRiesgoController -> buscar() {}/{}", idCotizacion, idCobertura);
        return ResponseEntity.ok(service.findByCoberturaRiesgoId(idCotizacion, idCobertura));
    }

    @Operation(
    summary = "Crear una cobertura de riesgo",
    description = "Registra una nueva cobertura de riesgo."
)
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Cobertura creada correctamente",
                content = @Content(
                        schema = @Schema(implementation = CoberturaRiesgoResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @PostMapping
    public ResponseEntity<CoberturaRiesgoResponseDto> createCoberturaRiesgo(@Valid @RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> guardar() {}", request);
        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCoberturaRiesgo(request));
    }

    @Operation(
    summary = "Actualizar una cobertura de riesgo",
    description = "Actualiza la información de una cobertura de riesgo existente."
)
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Cobertura actualizada correctamente",
                content = @Content(
                        schema = @Schema(implementation = CoberturaRiesgoResponseDto.class)
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Cobertura no encontrada",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Solicitud inválida",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @PutMapping("/{idCotizacion}/{idCobertura}")
    public ResponseEntity<CoberturaRiesgoResponseDto> updateCoberturaRiesgo(@PathVariable String idCotizacion,
        
        @PathVariable String idCobertura,
        
        @Valid @RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> actualizar() {}", request);
        return ResponseEntity.ok(service.updateCoberturaRiesgo(idCotizacion, idCobertura, request));
    }

    @Operation(
    summary = "Eliminar una cobertura de riesgo",
    description = "Elimina una cobertura de riesgo mediante su identificador."
)
    @ApiResponses({
        @ApiResponse(
                responseCode = "204",
                description = "Cobertura eliminada correctamente"
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Cobertura no encontrada",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Error interno del servidor",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponse.class)
                )
        )
    })
    @DeleteMapping("/{idCotizacion}/{idCobertura}")
    public ResponseEntity<Void> deleteCoberturaRiesgo(
        
        @PathVariable String idCotizacion, 
        
        @PathVariable String idCobertura) {
        
        log.info("CoberturaRiesgoController -> eliminar() {}/{}", idCotizacion, idCobertura);
        
        service.deleteCoberturaRiesgo(idCotizacion, idCobertura);
        
        return ResponseEntity.noContent().build();
    }
}