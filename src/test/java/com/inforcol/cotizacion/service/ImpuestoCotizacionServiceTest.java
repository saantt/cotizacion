package com.inforcol.cotizacion.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionPageResponse;
import com.inforcol.cotizacion.model.ImpuestoCotizacion.ImpuestoCotizacion;
import com.inforcol.cotizacion.repository.ImpuestoCotizacionRepository.ImpuestoCotizacionRepository;
import com.inforcol.cotizacion.service.ImpuestoCotizacionService.ImpuestoCotizacionServiceImpl;

@ExtendWith(MockitoExtension.class)
class ImpuestoCotizacionServiceTest {

    @Mock
    private ImpuestoCotizacionRepository repository;

    @InjectMocks
    private ImpuestoCotizacionServiceImpl service;

    @Test
    void listar_debeConsultarSoloLaPaginaSolicitadaYRetornarMetadatos() {
        ImpuestoCotizacion impuesto = new ImpuestoCotizacion(
                11,
                "COT-0011",
                "IVA 19%",
                new BigDecimal("19000.00"));
        Pageable pageableEsperado = PageRequest.of(
                1,
                10,
                Sort.by(Sort.Direction.ASC, "idImpuestoCot"));

        when(repository.findAll(pageableEsperado))
                .thenReturn(new PageImpl<>(List.of(impuesto), pageableEsperado, 21));

        ImpuestoCotizacionPageResponse resultado = service.listar(1, 10);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(repository).findAll(pageableCaptor.capture());
        assertEquals(1, pageableCaptor.getValue().getPageNumber());
        assertEquals(10, pageableCaptor.getValue().getPageSize());
        assertEquals("idImpuestoCot: ASC", pageableCaptor.getValue().getSort().toString());

        assertEquals(1, resultado.getContent().size());
        assertEquals(11, resultado.getContent().get(0).getIdImpuestoCot());
        assertEquals(21, resultado.getTotalElements());
        assertEquals(3, resultado.getTotalPages());
        assertEquals(1, resultado.getNumber());
        assertEquals(10, resultado.getSize());
        assertFalse(resultado.isFirst());
        assertFalse(resultado.isLast());
    }

    @Test
    void listar_sinRegistrosDebeRetornarPaginaVacia() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "idImpuestoCot"));
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(), pageable, 0));

        ImpuestoCotizacionPageResponse resultado = service.listar(0, 10);

        assertTrue(resultado.getContent().isEmpty());
        assertEquals(0, resultado.getTotalElements());
        assertEquals(0, resultado.getTotalPages());
        assertTrue(resultado.isFirst());
        assertTrue(resultado.isLast());
    }
}
