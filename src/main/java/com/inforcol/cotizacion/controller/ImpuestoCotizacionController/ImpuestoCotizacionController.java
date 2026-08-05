package com.inforcol.cotizacion.controller.ImpuestoCotizacionController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionPageResponse;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;
import com.inforcol.cotizacion.service.ImpuestoCotizacionService.ImpuestoCotizacionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@Validated
@RequestMapping("/api/impuestos-cotizacion")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(
        name = "Impuestos de cotización",
        description = "Operaciones para administrar los impuestos de una cotización")
@Slf4j
public class ImpuestoCotizacionController {

    private final ImpuestoCotizacionService service;

    public ImpuestoCotizacionController(ImpuestoCotizacionService service) {
        this.service = service;
    }

    @Operation(
            summary = "Crear un impuesto",
            description = "Registra un nuevo impuesto asociado a una cotización")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Impuesto creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de la solicitud no válidos")
    })
    @PostMapping
    public ResponseEntity<ImpuestoCotizacionResponse> crear(
            @Valid @RequestBody ImpuestoCotizacionRequest request) {

        log.info("Solicitud para crear impuesto de cotizacion");
        ImpuestoCotizacionResponse respuesta = service.crear(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(respuesta);
    }

    @Operation(
            summary = "Listar impuestos",
            description = "Obtiene una página de los impuestos de cotización registrados")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    @GetMapping
    public ResponseEntity<ImpuestoCotizacionPageResponse> listar(
            @RequestParam(defaultValue = "0")
            @Min(value = 0, message = "La página no puede ser negativa")
            int page,

            @RequestParam(defaultValue = "10")
            @Min(value = 1, message = "El tamaño de página debe ser mayor que cero")
            @Max(value = 100, message = "El tamaño de página no puede superar 100 registros")
            int size) {

        log.info("Solicitud para listar impuestos de cotizacion. Pagina: {}, tamano: {}", page, size);
        return ResponseEntity.ok(service.listar(page, size));
    }

    @Operation(
            summary = "Consultar un impuesto",
            description = "Busca un impuesto de cotización por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Impuesto encontrado"),
            @ApiResponse(responseCode = "400", description = "Identificador no válido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> buscarPorId(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        log.info("Solicitud para buscar impuesto de cotizacion con id: {}", id);
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @Operation(
            summary = "Actualizar un impuesto",
            description = "Actualiza los datos de un impuesto de cotización existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Impuesto actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Identificador o datos no válidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ImpuestoCotizacionResponse> actualizar(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id,

            @Valid
            @RequestBody
            ImpuestoCotizacionRequest request) {

        log.info("Solicitud para actualizar impuesto de cotizacion con id: {}", id);
        return ResponseEntity.ok(
                service.actualizar(id, request)
        );
    }


    @Operation(
            summary = "Eliminar un impuesto",
            description = "Elimina un impuesto de cotización por su identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Impuesto eliminado correctamente"),
            @ApiResponse(responseCode = "400", description = "Identificador no válido")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable
            @Positive(message = "El id debe ser mayor que cero")
            Integer id) {

        log.info("Solicitud para eliminar impuesto de cotizacion con id: {}", id);
        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
