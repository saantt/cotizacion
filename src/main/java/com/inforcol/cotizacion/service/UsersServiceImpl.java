package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inforcol.cotizacion.dto.LoginResponse;
import com.inforcol.cotizacion.dto.UsersDto;
import com.inforcol.cotizacion.mapper.UsersMapper;
import com.inforcol.cotizacion.model.Users;
import com.inforcol.cotizacion.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final UsersMapper mapper;

    public UsersServiceImpl(UsersRepository repository, UsersMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public UsersDto create(UsersDto dto) {
        if (repository.existsByUsername(dto.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }

        Users user = mapper.toEntity(dto);
        Users saved = repository.save(user);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsersDto> listAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(String username, String password) {
        Users user = repository.findAll()
                .stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElseThrow();

        String token = "token-" + user.getIdUser() + "-" + System.currentTimeMillis();
        return new LoginResponse(token, user.getUsername());
    }
}
