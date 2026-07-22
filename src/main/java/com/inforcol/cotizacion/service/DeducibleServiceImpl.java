package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleRequestDTO;
import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.model.Deducible;
import com.inforcol.cotizacion.repository.DeducibleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeducibleServiceImpl implements DeducibleService {

    private final DeducibleRepository repository;

    public DeducibleServiceImpl(DeducibleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DeducibleResponseDTO> listar() {

        List<Deducible> lista = repository.findAll();

        List<DeducibleResponseDTO> respuesta = new ArrayList<>();

        for (Deducible deducible : lista){

            DeducibleResponseDTO dto = new DeducibleResponseDTO();

            dto.setId_deducible(deducible.getId_deducible());
            dto.setPorcentaje(deducible.getPorcentaje());
            dto.setMonto_minimo(deducible.getMonto_minimo());

            respuesta.add(dto);

        }

        return respuesta;
    }

    @Override
    public DeducibleResponseDTO buscar(Long id) {

        Deducible deducible = repository.findById(id).orElse(null);

        if(deducible == null){
            return null;
        }

        DeducibleResponseDTO dto = new DeducibleResponseDTO();

        dto.setId_deducible(deducible.getId_deducible());
        dto.setPorcentaje(deducible.getPorcentaje());
        dto.setMonto_minimo(deducible.getMonto_minimo());

        return dto;
    }

    @Override
    public DeducibleResponseDTO guardar(DeducibleRequestDTO request) {

        Deducible deducible = new Deducible();

        deducible.setPorcentaje(request.getPorcentaje());
        deducible.setMonto_minimo(request.getMonto_minimo());

        Deducible guardado = repository.save(deducible);

        DeducibleResponseDTO dto = new DeducibleResponseDTO();

        dto.setId_deducible(guardado.getId_deducible());
        dto.setPorcentaje(guardado.getPorcentaje());
        dto.setMonto_minimo(guardado.getMonto_minimo());

        return dto;
    }

    @Override
    public DeducibleResponseDTO actualizar(Long id, DeducibleRequestDTO request) {

        Deducible deducible = repository.findById(id).orElse(null);

        if(deducible == null){
            return null;
        }

        deducible.setPorcentaje(request.getPorcentaje());
        deducible.setMonto_minimo(request.getMonto_minimo());

        Deducible actualizado = repository.save(deducible);

        DeducibleResponseDTO dto = new DeducibleResponseDTO();

        dto.setId_deducible(actualizado.getId_deducible());
        dto.setPorcentaje(actualizado.getPorcentaje());
        dto.setMonto_minimo(actualizado.getMonto_minimo());

        return dto;
    }

    @Override
    public void eliminar(Long id) {

        repository.deleteById(id);

    }

}
