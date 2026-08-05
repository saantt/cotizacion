package com.inforcol.cotizacion.service;

import java.util.List;

import com.inforcol.cotizacion.dto.LoginResponse;
import com.inforcol.cotizacion.dto.RegistroDto;
import com.inforcol.cotizacion.dto.UsersDto;

public interface UsersService {

    UsersDto create(RegistroDto dto);

    List<UsersDto> listAll();

    LoginResponse login(String username, String password);
}
