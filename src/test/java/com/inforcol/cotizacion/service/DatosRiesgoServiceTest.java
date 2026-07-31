package com.inforcol.cotizacion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoRequestDto;
import com.inforcol.cotizacion.dto.datos_riesgo.DatosRiesgoResponseDto;
import com.inforcol.cotizacion.mapper.DatosRiesgoMapper;
import com.inforcol.cotizacion.model.DatosRiesgo;
import com.inforcol.cotizacion.model.DatosRiesgo.TipoServicio;
import com.inforcol.cotizacion.repository.DatosRiesgoRepository;

@ExtendWith(MockitoExtension.class)
class DatosRiesgoServiceTest {

    @Mock
    DatosRiesgoRepository repository;

    @Mock
    DatosRiesgoMapper mapper;

    @InjectMocks
    DatosRiesgoServiceImpl service;

    @Test
    void obtenerTodos_debeMapearLista() {
        DatosRiesgo entidad = new DatosRiesgo();
        entidad.setIdCotizacion("1");
        entidad.setPlaca("ABC123");
        entidad.setCcTomador("123456");
        entidad.setIdEstado(1);
        entidad.setModelo(2020);
        entidad.setTipoServicio(TipoServicio.PARTICULAR);

        DatosRiesgoResponseDto dto = new DatosRiesgoResponseDto("1", "ABC123", "123456", 1, null, 2020, TipoServicio.PARTICULAR);

        when(repository.findAll()).thenReturn(List.of(entidad));
        when(mapper.modeloAResponseDto(entidad)).thenReturn(dto);

        List<DatosRiesgoResponseDto> result = service.obtenerTodos();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
    }

    @Test
    void guardar_guardaYRetornaDto() {
        DatosRiesgoRequestDto dtoIn = new DatosRiesgoRequestDto("1", "ABC123", "123456", 1, 2020, TipoServicio.PARTICULAR);
        DatosRiesgo entidad = new DatosRiesgo();
        entidad.setIdCotizacion("1");
        entidad.setPlaca("ABC123");
        entidad.setCcTomador("123456");
        entidad.setIdEstado(1);
        entidad.setModelo(2020);
        entidad.setTipoServicio(TipoServicio.PARTICULAR);

        DatosRiesgoResponseDto dtoOut = new DatosRiesgoResponseDto("1", "ABC123", "123456", 1, null, 2020, TipoServicio.PARTICULAR);

        when(mapper.dtoAEntidad(dtoIn)).thenReturn(entidad);
        when(repository.save(entidad)).thenReturn(entidad);
        when(mapper.modeloAResponseDto(entidad)).thenReturn(dtoOut);

        DatosRiesgoResponseDto result = service.guardar(dtoIn);

        assertEquals(dtoOut, result);
        verify(repository).save(entidad);
    }

    @Test
    void actualizar_existingId_actualizaYRetorna() {
        String id = "1";
        DatosRiesgoRequestDto dto = new DatosRiesgoRequestDto("1", "NEW123", "123456", 2, 2021, TipoServicio.PUBLICO);
        DatosRiesgo entidad = new DatosRiesgo();
        entidad.setIdCotizacion("1");

        DatosRiesgoResponseDto dtoOut = new DatosRiesgoResponseDto("1", "NEW123", "123456", 2, null, 2021, TipoServicio.PUBLICO);

        when(repository.findById(id)).thenReturn(Optional.of(entidad));
        // mapper.actualizarEntidad es void; verificaremos que se invoque
        when(repository.save(entidad)).thenReturn(entidad);
        when(mapper.modeloAResponseDto(entidad)).thenReturn(dtoOut);

        DatosRiesgoResponseDto result = service.actualizar(id, dto);

        assertEquals(dtoOut, result);
        verify(mapper).actualizarEntidad(dto, entidad);
        verify(repository).save(entidad);
    }

    @Test
    void actualizar_noExiste_lanzaException() {
        when(repository.findById("not-found")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.actualizar("not-found", new DatosRiesgoRequestDto()));

        assertTrue(ex.getMessage().contains("No se encontró el riesgo"));
    }

    @Test
    void eliminar_existing_eliminaYRetornaDto() {
        String id = "1";
        DatosRiesgo entidad = new DatosRiesgo();
        entidad.setIdCotizacion("1");
        entidad.setPlaca("ABC123");
        entidad.setCcTomador("123456");
        entidad.setIdEstado(1);
        entidad.setModelo(2020);
        entidad.setTipoServicio(TipoServicio.PARTICULAR);

        DatosRiesgoResponseDto dto = new DatosRiesgoResponseDto("1", "ABC123", "123456", 1, null, 2020, TipoServicio.PARTICULAR);

        when(repository.findById(id)).thenReturn(Optional.of(entidad));
        when(mapper.modeloAResponseDto(entidad)).thenReturn(dto);

        DatosRiesgoResponseDto result = service.eliminar(id);

        assertEquals(dto, result);
        verify(repository).delete(entidad);
    }

    @Test
    void eliminar_noExiste_lanzaException() {
        when(repository.findById("not-found")).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.eliminar("not-found"));

        assertTrue(ex.getMessage().contains("No se encontró el riesgo"));
    }
}
