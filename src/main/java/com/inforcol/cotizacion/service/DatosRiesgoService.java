package com.inforcol.cotizacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
import com.inforcol.cotizacion.model.DatosRiesgo;
import com.inforcol.cotizacion.repository.DatosRiesgoRepository;

@Service
public class DatosRiesgoService {

    private final DatosRiesgoRepository repository;

    public DatosRiesgoService(DatosRiesgoRepository repository) {
        this.repository = repository;
    }

    public List<DatosRiesgoDTO> obtenerTodos() {
        List<DatosRiesgo> entidades = repository.findAll();
        List<DatosRiesgoDTO> dtos = new ArrayList<>();

        for (DatosRiesgo entidad : entidades) {
            DatosRiesgoDTO dto = new DatosRiesgoDTO();
            
            dto.setIdCotizacion(entidad.getIdCotizacion());
            dto.setPlaca(entidad.getPlaca());
            dto.setCcTomador(entidad.getCcTomador());
            dto.setIdEstado(entidad.getIdEstado());
            dto.setFechaCotizacion(entidad.getFechaCotizacion());
            dto.setModelo(entidad.getModelo());
            dto.setTipoServicio(entidad.getTipoServicio());
            
            dtos.add(dto);
        }
        return dtos;
    }

    public void guardar(DatosRiesgoDTO dto) {
        DatosRiesgo entidad = new DatosRiesgo();
        
        entidad.setIdCotizacion(dto.getIdCotizacion());
        entidad.setPlaca(dto.getPlaca());
        entidad.setCcTomador(dto.getCcTomador());
        entidad.setIdEstado(dto.getIdEstado());
        entidad.setModelo(dto.getModelo());
        entidad.setTipoServicio(dto.getTipoServicio());
        
        if (dto.getFechaCotizacion() != null) {
            entidad.setFechaCotizacion(dto.getFechaCotizacion());
        }

        repository.save(entidad);
    }
}
