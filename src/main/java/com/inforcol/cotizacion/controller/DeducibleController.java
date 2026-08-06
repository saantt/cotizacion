package com.inforcol.cotizacion.controller;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.service.DeducibleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deducibles")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@Tag(
        name = "Deducibles",
        description = "API para la gestión de deducibles."
)
public class DeducibleController {

    private final DeducibleService service;

    public DeducibleController(DeducibleService service) {
        this.service = service;
    }

    @Operation(
            summary = "Listar deducibles",
            description = "Obtiene la lista completa de deducibles registrados."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista obtenida correctamente"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping
    public List<DeducibleResponseDTO> getAllDeducibles() {

        log.info("DeducibleController -> listar()");

        return service.getAllDeducibles();
    }

    @Operation(
            summary = "Buscar deducible por ID",
            description = "Obtiene un deducible utilizando su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Deducible encontrado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe un deducible con ese ID"
            )
    })
    @GetMapping("/{id}")
    public DeducibleResponseDTO findByDeducibleId(

            @Parameter(
                    description = "ID del deducible",
                    example = "1"
            )
            @PathVariable Long id) {

        log.info("DeducibleController -> buscar() {}", id);

        return service.findByDeducibleId(id);
    }

    @Operation(
            summary = "Crear deducible",
            description = "Registra un nuevo deducible en la base de datos."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Deducible creado correctamente"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos",
                    content = @Content(
                            schema = @Schema(
                                    implementation = String.class
                            )
                    )
            )
    })
    @PostMapping
    public DeducibleResponseDTO createDeducible(
            @Valid @RequestBody DeducibleRequestDTO request) {

        log.info("DeducibleController -> guardar() {}", request);

        return service.createDeducible(request);
    }

    @Operation(
            summary = "Actualizar deducible",
            description = "Actualiza los datos de un deducible existente."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Deducible actualizado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe el deducible"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos"
            )
    })
    @PutMapping("/{id}")
    public DeducibleResponseDTO updateDeducible(

            @Parameter(
                    description = "ID del deducible",
                    example = "1"
            )
            @PathVariable Long id,

            @Valid
            @RequestBody DeducibleRequestDTO request) {

        log.info("DeducibleController -> actualizar() {}", request);

        return service.updateDeducible(id, request);
    }

    @Operation(
            summary = "Eliminar deducible",
            description = "Elimina un deducible por su identificador."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Deducible eliminado correctamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe el deducible"
            )
    })
    @DeleteMapping("/{id}")
    public void deleteDeducible(

            @Parameter(
                    description = "ID del deducible",
                    example = "1"
            )
            @PathVariable Long id) {

        log.info("DeducibleController -> eliminar() {}", id);

        service.deleteDeducible(id);
    }
    @GetMapping("/page")
    public ResponseEntity<Page<DeducibleResponseDTO>> getAllDeduciblesPage(Pageable pageable){
        return ResponseEntity.ok(service.getAllDedudiblesPage(pageable));

    }

}