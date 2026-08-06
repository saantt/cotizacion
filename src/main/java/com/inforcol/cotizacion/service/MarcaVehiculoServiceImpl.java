package com.inforcol.cotizacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.deducibleDTO.DeducibleResponseDTO;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoRequestDto;
import com.inforcol.cotizacion.dto.marcavehiculo.MarcaVehiculoResponseDto;
import com.inforcol.cotizacion.mapper.MarcaVehiculoMapper;
import com.inforcol.cotizacion.model.MarcaVehiculo;
import com.inforcol.cotizacion.repository.MarcaVehiculoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarcaVehiculoServiceImpl implements MarcaVehiculoService {

    @Autowired
    private MarcaVehiculoRepository marcaVehiculoRepository;

    @Autowired
    private MarcaVehiculoMapper marcaVehiculoMapper;

    @Override
    public MarcaVehiculoResponseDto crearMarcaVehiculo(MarcaVehiculoRequestDto dto) {
        log.info("MarcaVehiculoServiceImpl ->  crearMarcaVehiculo() {}", dto);
        MarcaVehiculo marcaVehiculo = marcaVehiculoMapper.toEntity(dto);
        return marcaVehiculoMapper.toDto(marcaVehiculoRepository.save(marcaVehiculo));
    }

    @Override
    public List<MarcaVehiculoResponseDto> obtenerTodasMarcaVehiculo() {
        List<MarcaVehiculoResponseDto> marcas = marcaVehiculoRepository.findAll()
                .stream()
                .map(marcaVehiculoMapper::toDto)
                .toList();

        log.info("MarcaVehiculoServiceImpl -> obtenerTodasMarcaVehiculo() {}", marcas);

        return marcas;
    }

    @Override
    public MarcaVehiculoResponseDto obtenerMarcaVehiculoPorId(Long id) {
        log.info("MarcaVehiculoServiceImpl -> obtenerMarcaVehiculoPorId() - id: {}", id);
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: " + id));

        return marcaVehiculoMapper.toDto(marcaVehiculo);
    }

    @Override
    public MarcaVehiculoResponseDto actualizarMarcaVehiculo(Long id, MarcaVehiculoRequestDto dto) {
        log.info("MarcaVehiculoServiceImpl ->  actualizarMarcaVehiculo() {}", dto);
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: " + id));

        marcaVehiculoMapper.updateEntityFromDto(dto, marcaVehiculo);
        return marcaVehiculoMapper.toDto(marcaVehiculoRepository.save(marcaVehiculo));
    }

    @Override
    public void eliminarMarcaVehiculo(Long id) {
        log.info("MarcaVehiculoServiceImpl ->  eliminarMarcaVehiculo() {}", id);
        MarcaVehiculo marcaVehiculo = marcaVehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca de vehículo no encontrada con ID: " + id));
        marcaVehiculoRepository.delete(marcaVehiculo);
    }

    @Override
    public MarcaVehiculoResponseDto obtenerMarcaPorNombre(String nombre) {
        MarcaVehiculo marca = marcaVehiculoRepository.findByNombreMarca(nombre);

        if (marca == null) {
            throw new RuntimeException("No existe una marca con el nombre: " + nombre);
        }

        return marcaVehiculoMapper.toDto(marca);
    }

    @Override
    public List<MarcaVehiculoResponseDto> obtenerMarcasPorPais(String pais) {
        return marcaVehiculoRepository.findByPaisOrigen(pais)
                .stream()
                .map(marcaVehiculoMapper::toDto)
                .toList();
    }

    @Override
    public String validarExistenciaMarca(String nombreMarca) {

        boolean existe = marcaVehiculoRepository.existsByNombreMarca(nombreMarca);

        if (existe) {
            return "La marca de vehículo '" + nombreMarca + "' ya existe.";
        }

        return "La marca de vehículo '" + nombreMarca + "' no existe.";
    }

    @Override
    public MarcaVehiculoResponseDto obtenerMarcaPorAbreviatura(String abreviatura) {
        MarcaVehiculo marca = marcaVehiculoRepository.findByAbreviatura(abreviatura);

        if (marca == null) {
            throw new RuntimeException("No existe una marca con la abreviatura: " + abreviatura);
        }

        return marcaVehiculoMapper.toDto(marca);
    }

    @Override
    public Page<MarcaVehiculoResponseDto> obtenerTodasMarcaVehiculoPagina(Pageable pageable) {
        return marcaVehiculoRepository.findAll(pageable).map(marcaVehiculoMapper::toDto);
    }

}
