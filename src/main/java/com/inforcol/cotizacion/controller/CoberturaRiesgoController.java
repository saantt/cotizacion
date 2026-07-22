package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.service.CoberturaRiesgoService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/coberturariesgo")
@Slf4j
public class CoberturaRiesgoController {

    private final CoberturaRiesgoService service;

    public CoberturaRiesgoController(CoberturaRiesgoService service) {
        this.service = service;
    }

    @GetMapping
    public List<CoberturaRiesgoResponseDto> getAllCoberturaRiesgo() {
        log.info("CoberturaRiesgoController -> listar()");
        return service.getAllCoberturaRiesgo();
    }

    @GetMapping("/{idCotizacion}/{idCobertura}")
    public CoberturaRiesgoResponseDto findByCoberturaRiesgoId(@PathVariable String idCotizacion,
                                                               @PathVariable String idCobertura) {
        log.info("CoberturaRiesgoController -> buscar() {}/{}", idCotizacion, idCobertura);
        return service.findByCoberturaRiesgoId(idCotizacion, idCobertura);
    }

    @PostMapping
    public CoberturaRiesgoResponseDto createCoberturaRiesgo(@RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> guardar() {}", request);
        return service.createCoberturaRiesgo(request);
    }

    @PutMapping("/{idCotizacion}/{idCobertura}")
    public CoberturaRiesgoResponseDto updateCoberturaRiesgo(@PathVariable String idCotizacion,
                                                             @PathVariable String idCobertura,
                                                             @RequestBody CoberturaRiesgoRequestDto request) {
        log.info("CoberturaRiesgoController -> actualizar() {}", request);
        return service.updateCoberturaRiesgo(idCotizacion, idCobertura, request);
    }

    @DeleteMapping("/{idCotizacion}/{idCobertura}")
    public void deleteCoberturaRiesgo(@PathVariable String idCotizacion, @PathVariable String idCobertura) {
        log.info("CoberturaRiesgoController -> eliminar() {}/{}", idCotizacion, idCobertura);
        service.deleteCoberturaRiesgo(idCotizacion, idCobertura);
    }
}