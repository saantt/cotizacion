package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.mapper.DeducibleMapper;
import com.inforcol.cotizacion.model.Deducible;
import com.inforcol.cotizacion.repository.DeducibleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeducibleServiceImpl implements DeducibleService {

    private final DeducibleRepository repository;
    private final DeducibleMapper mapper;

    public DeducibleServiceImpl(DeducibleRepository repository,
            DeducibleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<DeducibleResponseDTO> getAllDeducibles() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public DeducibleResponseDTO findByDeducibleId(Long id) {

        Deducible entity = repository.findById(id)
                .orElseThrow();

        return mapper.toResponseDTO(entity);
    }

    @Override
    public DeducibleResponseDTO createDeducible(DeducibleRequestDTO request) {

        Deducible entity = mapper.toEntity(request);

        Deducible guardado = repository.save(entity);

        return mapper.toResponseDTO(guardado);

    }

    @Override
    public DeducibleResponseDTO updateDeducible(Long id,
            DeducibleRequestDTO request) {

        Deducible entity = repository.findById(id)
                .orElseThrow();

        entity.setPorcentaje(request.getPorcentaje());
        entity.setMonto_minimo(request.getMonto_minimo());

        Deducible actualizado = repository.save(entity);

        return mapper.toResponseDTO(actualizado);
    }

    @Override
    public void deleteDeducible(Long id) {

        repository.deleteById(id);

    }

}
