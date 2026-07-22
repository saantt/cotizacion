package com.inforcol.cotizacion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.DTO.datos_riesgo.DatosRiesgoDTO;
import com.inforcol.cotizacion.model.DatosRiesgo;
import com.inforcol.cotizacion.repository.DatosRiesgoRepository;

@Service // Única anotación necesaria. Quitamos @Data y @AllArgsConstructor
public class DatosRiesgoService {

    // 1. Declaramos el repositorio como final (Inmutabilidad obligatoria de Spring)
    private final DatosRiesgoRepository repository;

    // 2. Constructor explícito y manual. Cero magia de Lombok aquí.
    // Esto garantiza que Spring inyecte el repositorio de forma transparente.
    public DatosRiesgoService(DatosRiesgoRepository repository) {
        this.repository = repository;
    }

    // 3. Obtener todos los registros de forma sencilla
    public List<DatosRiesgoDTO> obtenerTodos() {
        List<DatosRiesgo> entidades = repository.findAll();
        List<DatosRiesgoDTO> dtos = new ArrayList<>();

        for (DatosRiesgo entidad : entidades) {
            DatosRiesgoDTO dto = new DatosRiesgoDTO();
            
            // Usamos setters tradicionales para no depender de constructores rígidos en el DTO
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

    // 4. Guardar un registro de forma sencilla
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
