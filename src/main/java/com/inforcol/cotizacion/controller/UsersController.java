package com.inforcol.cotizacion.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inforcol.cotizacion.dto.LoginRequest;
import com.inforcol.cotizacion.dto.LoginResponse;
import com.inforcol.cotizacion.dto.RegistroDto;
import com.inforcol.cotizacion.dto.UsersDto;
import com.inforcol.cotizacion.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:4200")
@Slf4j
@Tag(name = "Users", description = "Operaciones sobre usuarios")
public class UsersController {

    private final UsersService service;

    public UsersController(UsersService service) {
        this.service = service;
    }

    @Operation(summary = "Registrar usuario", description = "Crea y registra un nuevo usuario con nombre, apellido, correo, usuario y contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<UsersDto> register(@Valid @RequestBody RegistroDto dto) {
        log.info("UsersController -> register usuario: {}", dto.getUsername());
        UsersDto created = service.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Listar usuarios", description = "Obtiene todos los usuarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios obtenidos correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping
    public ResponseEntity<List<UsersDto>> listAll() {
        log.info("UsersController -> listAll usuarios");
        return ResponseEntity.ok(service.listAll());
    }

    @Operation(summary = "Login de usuario", description = "Valida el usuario y retorna token si las credenciales son correctas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login exitoso"),
            @ApiResponse(responseCode = "400", description = "Usuario o contraseña inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest dto) {
        log.info("UsersController -> login usuario: {}", dto.getUsername());
        LoginResponse response = service.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(response);
    }
}
