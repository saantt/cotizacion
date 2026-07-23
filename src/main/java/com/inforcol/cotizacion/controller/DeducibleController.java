package com.inforcol.cotizacion.controller;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.service.DeducibleService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deducibles")
@Slf4j
public class DeducibleController {

    private final DeducibleService service;

    public DeducibleController(DeducibleService service) {
        this.service = service;
    }

    @GetMapping
    public List<DeducibleResponseDTO> getAllDeducibles() {
        log.info("DeducibleController -> listar() {}", service.getAllDeducibles());
        return service.getAllDeducibles();
    }

    @GetMapping("/{id}")
    public DeducibleResponseDTO findByDeducibleId(@PathVariable Long id) {
        log.info("DeducibleController -> buscar() {}", id);
        return service.findByDeducibleId(id);
    }

    @PostMapping
    public DeducibleResponseDTO createDeducible(@Valid @RequestBody DeducibleRequestDTO request) {
        log.info("DeducibleController -> guardar() {}", request);
        return service.createDeducible(request);
    }

    @PutMapping("/{id}")
    public DeducibleResponseDTO updateDeducible(
            @Valid @PathVariable Long id,
            @RequestBody DeducibleRequestDTO request) {
        log.info("DeducibleController -> actualizar () {}", request);

        return service.updateDeducible(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteDeducible(@PathVariable Long id) {
        log.info("DeducibleController -> eliminar() {}", id);
        service.deleteDeducible(id);
    }

}