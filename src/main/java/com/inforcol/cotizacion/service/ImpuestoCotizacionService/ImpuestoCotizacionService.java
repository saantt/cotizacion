package com.inforcol.cotizacion.service.ImpuestoCotizacionService;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionPageResponse;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ImpuestoCotizacionService {

    ImpuestoCotizacionResponse crear(ImpuestoCotizacionRequest request);

    ImpuestoCotizacionPageResponse listar(int page, int size);

    Page<ImpuestoCotizacionResponse> getAllImpuestosCotizacionPage(Pageable page);

    ImpuestoCotizacionResponse buscarPorId(Integer id);

    ImpuestoCotizacionResponse actualizar(
            Integer id,
            ImpuestoCotizacionRequest request);

    void eliminar(Integer id);
}
