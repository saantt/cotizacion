package com.inforcol.cotizacion.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoRequestDto;
import com.inforcol.cotizacion.dto.CoberturaRiesgoDto.CoberturaRiesgoResponseDto;
import com.inforcol.cotizacion.exception.ResourceNotFoundException;
import com.inforcol.cotizacion.model.CoberturaRiesgo;
import com.inforcol.cotizacion.model.CoberturaRiesgoId;
import com.inforcol.cotizacion.repository.CoberturaRiesgoRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Service
@Slf4j

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
        log.info("Obteniendo todas las coberturas de riesgo");

        return coberturaRiesgoRepository.findAll()
                .stream()
                .map(coberturaRiesgoServiceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CoberturaRiesgoResponseDto findByCoberturaRiesgoId(String idCotizacion, String idCobertura) {
        
        log.info("Buscando cobertura. Cotización: {}, Cobertura: {}",
                idCotizacion, idCobertura);
        
        CoberturaRiesgoId id = new CoberturaRiesgoId(idCotizacion, idCobertura);
        return coberturaRiesgoRepository.findById(id)
                .map(coberturaRiesgoServiceMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobertura del riesgo no encontrada para: " + idCotizacion + " / " + idCobertura));
    }

    @Override
    public CoberturaRiesgoResponseDto createCoberturaRiesgo(CoberturaRiesgoRequestDto request) {
        
        log.info("Creando cobertura de riesgo: {]", request);

        CoberturaRiesgo entity = coberturaRiesgoServiceMapper.toEntity(request);
        CoberturaRiesgo saved = coberturaRiesgoRepository.save(entity);
        
        log.info("Cobertura creada correctamente");
        
        return coberturaRiesgoServiceMapper.toDto(saved);
    }

    @Override
    public CoberturaRiesgoResponseDto updateCoberturaRiesgo(String idCotizacion, String idCobertura, CoberturaRiesgoRequestDto request) {

        log.info("Actualizando cobertura {} - {}", idCotizacion, idCobertura);

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

        log.info("Eliminando cobertura {} - {}", idCotizacion, idCobertura);

        CoberturaRiesgoId id = new CoberturaRiesgoId(idCotizacion, idCobertura);
        CoberturaRiesgo existing = coberturaRiesgoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cobertura del riesgo no encontrada para: " + idCotizacion + " / " + idCobertura));
        coberturaRiesgoRepository.delete(existing);

    }
    @Override
        public Page<CoberturaRiesgoResponseDto> getAllCoberturaRiesgoPage(Pageable page){
            return coberturaRiesgoRepository.findAll(page).map(coberturaRiesgoServiceMapper::toDto);
        }
}