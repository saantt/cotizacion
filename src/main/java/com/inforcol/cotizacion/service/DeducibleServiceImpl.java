package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.exception.BadRequestException;
import com.inforcol.cotizacion.exception.ResourceNotFoundException;
import com.inforcol.cotizacion.mapper.DeducibleMapper;
import com.inforcol.cotizacion.model.Deducible;
import com.inforcol.cotizacion.repository.DeducibleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeducibleServiceImpl implements DeducibleService {

    private final DeducibleRepository repository;
    private final DeducibleMapper mapper;

    @Override
    public List<DeducibleResponseDTO> getAllDeducibles() {

        List<Deducible> deducibles = repository.findAll();

        return deducibles.stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    @Override
    public DeducibleResponseDTO findByDeducibleId(Long id) {

        Deducible deducible = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un deducible con id: " + id));

        return mapper.toResponseDTO(deducible);
    }

    @Override
    public DeducibleResponseDTO createDeducible(DeducibleRequestDTO request) {

        validarDeducible(request);

        Deducible deducible = mapper.toEntity(request);

        Deducible guardado = repository.save(deducible);

        return mapper.toResponseDTO(guardado);
    }

    @Override
    public DeducibleResponseDTO updateDeducible(Long id,
                                                DeducibleRequestDTO request) {

        validarDeducible(request);

        Deducible deducible = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un deducible con id: " + id));

        deducible.setPorcentaje(request.getPorcentaje());
        deducible.setMonto_minimo(request.getMonto_minimo());

        Deducible actualizado = repository.save(deducible);

        return mapper.toResponseDTO(actualizado);
    }

    @Override
    public void deleteDeducible(Long id) {

        Deducible deducible = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "No existe un deducible con id: " + id));

        repository.delete(deducible);
    }

    /**
     * Validaciones de negocio
     */
    private void validarDeducible(DeducibleRequestDTO request) {

        if (request.getPorcentaje() == null) {
            throw new BadRequestException("El porcentaje es obligatorio.");
        }

        if (request.getMonto_minimo() == null) {
            throw new BadRequestException("El monto mínimo es obligatorio.");
        }

        if (request.getPorcentaje() < 0 || request.getPorcentaje() > 100) {
            throw new BadRequestException(
                    "El porcentaje debe estar entre 0 y 100.");
        }

        if (request.getMonto_minimo() <= 0) {
            throw new BadRequestException(
                    "El monto mínimo debe ser mayor que cero.");
        }

        boolean existe = repository.findAll()
                .stream()
                .anyMatch(d ->
                        d.getPorcentaje() == request.getPorcentaje()
                        && d.getMonto_minimo().equals(request.getMonto_minimo()));

        if (existe) {
            throw new BadRequestException(
                    "Ya existe un deducible con el mismo porcentaje y monto mínimo.");
        }
    }
}