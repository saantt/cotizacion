package com.inforcol.cotizacion.mapper;

import org.mapstruct.Mapper;
import com.inforcol.cotizacion.dto.UsersDto;
import com.inforcol.cotizacion.model.Users;

@Mapper(componentModel = "spring")
public interface UsersMapper {

    UsersDto toDto(Users users);

    Users toEntity(UsersDto dto);
}
