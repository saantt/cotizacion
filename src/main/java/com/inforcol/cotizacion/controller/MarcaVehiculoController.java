package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/marcavehiculo")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Tag(name = "Marca Vehículo", description = "API para la gestión de marcas de vehículos")
public class MarcaVehiculoController {

    @Autowired
    private MarcaVehiculoService marcaVehiculoService;

    @Operation(summary = "Crear una nueva marca de vehículo", description = "Registra una nueva marca de vehículo en el sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Marca creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping
    public ResponseEntity<?> crearMarcaVehiculo(
            @Valid @RequestBody MarcaVehiculoRequestDto dto,
            BindingResult result) {

        log.info("MarcaVehiculoController -> crearMarcaVehiculo() {}", dto);

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        MarcaVehiculoResponseDto response = marcaVehiculoService.crearMarcaVehiculo(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtener todas las marcas de vehículos", description = "Consulta el listado completo de marcas registradas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<MarcaVehiculoResponseDto>> obtenerTodasMarcaVehiculo() {

        return ResponseEntity.ok(
                marcaVehiculoService.obtenerTodasMarcaVehiculo());
    }

    @Operation(summary = "Buscar marca de vehículo por ID", description = "Obtiene una marca específica mediante su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MarcaVehiculoResponseDto> obtenerMarcaVehiculoPorId(
            @Parameter(description = "ID de la marca", required = true, example = "1") @PathVariable Long id) {

        return ResponseEntity.ok(
                marcaVehiculoService.obtenerMarcaVehiculoPorId(id));
    }

    @Operation(summary = "Actualizar una marca de vehículo", description = "Modifica la información de una marca existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarMarcaVehiculo(
            @Parameter(description = "ID de la marca", example = "1") @PathVariable Long id,
            @Valid @RequestBody MarcaVehiculoRequestDto dto,
            BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        return ResponseEntity.ok(
                marcaVehiculoService.actualizarMarcaVehiculo(id, dto));
    }

    @Operation(summary = "Eliminar una marca de vehículo", description = "Elimina una marca mediante su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Marca eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMarcaVehiculo(
            @Parameter(description = "ID de la marca", example = "1") @PathVariable Long id) {

        marcaVehiculoService.eliminarMarcaVehiculo(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar marca por nombre", description = "Consulta una marca utilizando su nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<MarcaVehiculoResponseDto> obtenerMarcaPorNombre(
            @Parameter(description = "Nombre de la marca", example = "Toyota") @PathVariable String nombre) {

        return ResponseEntity.ok(
                marcaVehiculoService.obtenerMarcaPorNombre(nombre));
    }

    @Operation(summary = "Buscar marcas por país de origen", description = "Obtiene las marcas asociadas a un país")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
            @ApiResponse(responseCode = "404", description = "No existen marcas para el país")
    })
    @GetMapping("/pais/{pais}")
    public ResponseEntity<List<MarcaVehiculoResponseDto>> obtenerMarcasPorPais(
            @Parameter(description = "País de origen", example = "Japón") @PathVariable String pais) {

        return ResponseEntity.ok(
                marcaVehiculoService.obtenerMarcasPorPais(pais));
    }

    @Operation(summary = "Validar existencia de una marca", description = "Verifica si una marca ya está registrada")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Validación realizada correctamente")
    })
    @GetMapping("/existe/{nombreMarca}")
    public ResponseEntity<String> validarExistenciaMarca(
            @Parameter(description = "Nombre de la marca a validar", example = "Toyota") @PathVariable String nombreMarca) {

        return ResponseEntity.ok(
                marcaVehiculoService.validarExistenciaMarca(nombreMarca));
    }

    @Operation(summary = "Buscar marca por abreviatura", description = "Obtiene una marca mediante su abreviatura")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca no encontrada")
    })
    @GetMapping("/abreviatura/{abreviatura}")
    public ResponseEntity<MarcaVehiculoResponseDto> obtenerMarcaPorAbreviatura(
            @Parameter(description = "Abreviatura de la marca", example = "BMW") @PathVariable String abreviatura) {

        return ResponseEntity.ok(
                marcaVehiculoService.obtenerMarcaPorAbreviatura(abreviatura));
    }

}