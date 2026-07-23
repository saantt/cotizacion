package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;
import com.inforcol.cotizacion.mapper.MarcaVehiculoMapper;
import com.inforcol.cotizacion.model.MarcaVehiculo;
import com.inforcol.cotizacion.repository.MarcaVehiculoRepository;

@Service
public class MarcaVehiculoService {

    @Autowired
    private MarcaVehiculoRepository marcaVehiculoRepository;

    @Autowired
    private MarcaVehiculoMapper marcaVehiculoMapper;
 
    public MarcaVehiculoService(MarcaVehiculoRepository marcaVehiculoRepository, MarcaVehiculoMapper marcaVehiculoMapper) {
        this.marcaVehiculoRepository = marcaVehiculoRepository;
        this.marcaVehiculoMapper = marcaVehiculoMapper;
    }
 
    public MarcaVehiculoResponseDto crearMarcaVehiculo(MarcaVehiculoRequestDto dto) {
        MarcaVehiculo marcaVehiculo = marcaVehiculoMapper.toEntity(dto);
        return marcaVehiculoMapper.toDto(marcaVehiculoRepository.save(marcaVehiculo));
    }
 
    public List<MarcaVehiculoResponseDto> obtenerTodasMarcaVehiculo() {
        return marcaVehiculoRepository.findAll()
                .stream()
                .map(marcaVehiculoMapper::toDto)
                .toList();
    }
 
    public MarcaVehiculoResponseDto obtenerMarcaVehiculoPorId(Long id) {
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: "+ id));
 
        return marcaVehiculoMapper.toDto(marcaVehiculo);
    }
 
    public MarcaVehiculoResponseDto actualizarMarcaVehiculo(Long id, MarcaVehiculoRequestDto dto) {
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: "+ id));
 
        marcaVehiculoMapper.updateEntityFromDto(dto, marcaVehiculo);
        return marcaVehiculoMapper.toDto(marcaVehiculoRepository.save(marcaVehiculo));
    }

    public void eliminarMarcaVehiculo(Long id) {
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: "+ id));
        marcaVehiculoRepository.delete(marcaVehiculo);
    }

}
