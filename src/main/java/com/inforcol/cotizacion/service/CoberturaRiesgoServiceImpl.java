package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.model.CoberturaRiesgo;
import com.inforcol.cotizacion.model.CoberturaRiesgoId;
import com.inforcol.cotizacion.repository.CoberturaRiesgoRepository;

@Service
public class CoberturaRiesgoServiceImpl implements CoberturaRiesgoService {

    private final CoberturaRiesgoRepository coberturaRiesgoRepository;
    private final CoberturaRiesgoServiceMapper coberturaRiesgoServiceMapper;

    public CoberturaRiesgoServiceImpl(CoberturaRiesgoRepository coberturaRiesgoRepository,
                                       CoberturaRiesgoServiceMapper coberturaRiesgoServiceMapper) {
        this.coberturaRiesgoRepository = coberturaRiesgoRepository;
        this.coberturaRiesgoServiceMapper = coberturaRiesgoServiceMapper;
    }

    @Override
    public List<CoberturaRiesgoResponseDto> getAllCoberturaRiesgo() {
        return coberturaRiesgoRepository.findAll()
                .stream()
                .map(coberturaRiesgoServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CoberturaRiesgoResponseDto findByCoberturaRiesgoId(String idCotizacion, String idCobertura) {
        CoberturaRiesgoId id = new CoberturaRiesgoId(idCotizacion, idCobertura);
        return coberturaRiesgoRepository.findById(id)
                .map(coberturaRiesgoServiceMapper::toDto)
                .orElseThrow(() -> new RuntimeException(
                        "Cobertura del riesgo no encontrada para: " + idCotizacion + " / " + idCobertura));
    }

    @Override
    public CoberturaRiesgoResponseDto createCoberturaRiesgo(CoberturaRiesgoRequestDto request) {
        CoberturaRiesgo entity = coberturaRiesgoServiceMapper.toEntity(request);
        CoberturaRiesgo saved = coberturaRiesgoRepository.save(entity);
        return coberturaRiesgoServiceMapper.toDto(saved);
    }

    @Override
    public CoberturaRiesgoResponseDto updateCoberturaRiesgo(String idCotizacion, String idCobertura, CoberturaRiesgoRequestDto request) {
        CoberturaRiesgoId id = new CoberturaRiesgoId(idCotizacion, idCobertura);
        CoberturaRiesgo existing = coberturaRiesgoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Cobertura del riesgo no encontrada para: " + idCotizacion + " / " + idCobertura));

        existing.setIdDeducible(request.getIdDeducible());
        existing.setPrimaCobertura(request.getPrimaCobertura());

        return coberturaRiesgoServiceMapper.toDto(coberturaRiesgoRepository.save(existing));
    }

    @Override
    public void deleteCoberturaRiesgo(String idCotizacion, String idCobertura) {
        CoberturaRiesgoId id = new CoberturaRiesgoId(idCotizacion, idCobertura);
        CoberturaRiesgo existing = coberturaRiesgoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Cobertura del riesgo no encontrada para: " + idCotizacion + " / " + idCobertura));
        coberturaRiesgoRepository.delete(existing);
    }
}