package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.CobeturaRiesgoDto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CobeturaRiesgoDto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.service.CoberturaRiesgoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(
    name = "Cobertura Riesgo",
    description = "API para la gestión de coberturas de riesgo de las cotizaciones"
)

@RestController
@CrossOrigin(origins = "http://localhost//4200")
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
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cobertura encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public List<CoberturaRiesgoResponseDto> getAllCoberturaRiesgo() {
        log.info("CoberturaRiesgoController -> listar()");
        return service.getAllCoberturaRiesgo();
    }

    @Operation(
    summary = "Buscar una cobertura de riesgo",
    description = "Obtiene una cobertura de riesgo mediante el id de la cotización y el id de la cobertura."
)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cobertura encontrada"),
        @ApiResponse(responseCode = "404", description = "Cobertura no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("/{idCotizacion}/{idCobertura}")
    public CoberturaRiesgoResponseDto findByCoberturaRiesgoId(@PathVariable String idCotizacion,
                                                               @PathVariable String idCobertura) {
        log.info("CoberturaRiesgoController -> buscar() {}/{}", idCotizacion, idCobertura);
        return service.findByCoberturaRiesgoId(idCotizacion, idCobertura);
    }

    @Operation(
    summary = "Crear una cobertura de riesgo",
    description = "Registra una nueva cobertura de riesgo."
)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cobertura creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public CoberturaRiesgoResponseDto createCoberturaRiesgo(@RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> guardar() {}", request);
        return service.createCoberturaRiesgo(request);
    }

    @Operation(
    summary = "Actualizar una cobertura de riesgo",
    description = "Actualiza la información de una cobertura de riesgo existente."
)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cobertura actualizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
        @ApiResponse(responseCode = "404", description = "Cobertura no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{idCotizacion}/{idCobertura}")
    public CoberturaRiesgoResponseDto updateCoberturaRiesgo(@PathVariable String idCotizacion,
                                                             @PathVariable String idCobertura,
                                                             @RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> actualizar() {}", request);
        return service.updateCoberturaRiesgo(idCotizacion, idCobertura, request);
    }

    @Operation(
    summary = "Eliminar una cobertura de riesgo",
    description = "Elimina una cobertura de riesgo mediante su identificador."
)
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cobertura eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Cobertura no encontrada"),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{idCotizacion}/{idCobertura}")
    public void deleteCoberturaRiesgo(@PathVariable String idCotizacion, @PathVariable String idCobertura) {
        log.info("CoberturaRiesgoController -> eliminar() {}/{}", idCotizacion, idCobertura);
        service.deleteCoberturaRiesgo(idCotizacion, idCobertura);
    }
}