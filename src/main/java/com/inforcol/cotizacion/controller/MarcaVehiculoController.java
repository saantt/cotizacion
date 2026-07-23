package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;
import com.inforcol.cotizacion.service.MarcaVehiculoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/marcavehiculo")
@Slf4j
public class MarcaVehiculoController {

    @Autowired
    private MarcaVehiculoService marcaVehiculoService;

    @PostMapping
    public ResponseEntity<?> crearMarcaVehiculo(@Valid @RequestBody MarcaVehiculoRequestDto dto, BindingResult result) {
        log.info("MarcaVehiculoController ->  crearMarcaVehiculo() {}", dto);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        MarcaVehiculoResponseDto response = marcaVehiculoService.crearMarcaVehiculo(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MarcaVehiculoResponseDto>> obtenerTodasMarcaVehiculo() {
        log.info("MarcaVehiculoController -> obtenerTodasMarcaVehiculo() {}", marcaVehiculoService.obtenerTodasMarcaVehiculo());
        List<MarcaVehiculoResponseDto> marcs = marcaVehiculoService.obtenerTodasMarcaVehiculo();
        return ResponseEntity.ok(marcs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarcaVehiculoResponseDto> obtenerMarcaVehiculoPorId(@Valid @PathVariable Long id) {
        log.info("MarcaVehiculoController -> obtenerMarcaVehiculoPorId() {}", marcaVehiculoService.obtenerMarcaVehiculoPorId(id));
        return ResponseEntity.ok(marcaVehiculoService.obtenerMarcaVehiculoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMarcaVehiculo(@Valid @PathVariable Long id, @RequestBody MarcaVehiculoRequestDto dto, BindingResult result) {
        log.info("MarcaVehiculoController ->  actualizarMarcaVehiculo() {}", dto);
        if(result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        return ResponseEntity.ok(marcaVehiculoService.actualizarMarcaVehiculo(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MarcaVehiculoResponseDto> eliminarMarcaVehiculo(@Valid @PathVariable Long id) {
        log.info("MarcaVehiculoController ->  eliminarMarcaVehiculo() {}", id);
        marcaVehiculoService.eliminarMarcaVehiculo(id);
        return ResponseEntity.noContent().build();
    }

}
