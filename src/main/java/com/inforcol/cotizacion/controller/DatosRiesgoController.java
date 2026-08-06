package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoRequestDto;
import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoResponseDto;
import com.inforcol.cotizacion.service.DatosRiesgoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/datos-riesgo")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Tag(name = "DatosRiesgo", description = "Operaciones sobre datos de riesgo")
public class DatosRiesgoController {

    @Autowired
    private DatosRiesgoService service;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Devuelve todos los datos de riesgo")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Lista de datos de riesgo")
    })
    public List<DatosRiesgoResponseDto> listarTodos() {
        log.info("DatosRiesgoController.listarTodos() - Listando todos los datos de riesgo");
        return service.obtenerTodos();
    }

    @GetMapping("/{placa}")
    @Operation(summary = "Obtener por placa", description = "Busca un dato de riesgo por la matrícula/placa")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Dato encontrado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public ResponseEntity<DatosRiesgoResponseDto> obtenerPorPlaca(@Valid @PathVariable String placa) {
        log.info("DatosRiesgoController.obtenerPorPlaca() - Buscando dato de riesgo por placa: {}", placa);
        return ResponseEntity.ok(service.obtenerPorPlaca(placa));
    }

    @PostMapping
    @Operation(summary = "Crear dato de riesgo", description = "Crea un nuevo registro de datos de riesgo")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Creado"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<DatosRiesgoResponseDto> crear(@Valid @RequestBody DatosRiesgoRequestDto dto) {
        log.info("DatosRiesgoController.crear() - Creando un nuevo dato de riesgo: {}", dto);
        DatosRiesgoResponseDto respuesta = service.guardar(dto);
        return ResponseEntity.status(201).body(respuesta);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar dato", description = "Actualiza un dato de riesgo existente por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualizado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public ResponseEntity<DatosRiesgoResponseDto> actualizar(
            @PathVariable String id,
            @Valid @RequestBody DatosRiesgoRequestDto dto
    ) {
        log.info("DatosRiesgoController.actualizar() - Actualizando dato de riesgo con ID: {}", id);

        DatosRiesgoResponseDto respuesta = service.actualizar(id, dto);

        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar dato", description = "Elimina un dato de riesgo por ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eliminado"),
        @ApiResponse(responseCode = "404", description = "No encontrado")
    })
    public DatosRiesgoResponseDto eliminar(@PathVariable String id) {
        log.info("DatosRiesgoController.eliminar() - Eliminando dato de riesgo con ID: {}", id);
        return service.eliminar(id);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<DatosRiesgoResponseDto>> listarTodosPaginado(Pageable page) {
        log.info("DatosRiesgoController.listarTodosPaginado() - Listando todos los datos de riesgo paginados");
        return ResponseEntity.ok(service.obtenerTodosPaginado(page));
    }

}
