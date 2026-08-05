package com.inforcol.cotizacion.service.ImpuestoCotizacionService;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionPageResponse;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;

public interface ImpuestoCotizacionService {

    ImpuestoCotizacionResponse crear(ImpuestoCotizacionRequest request);

    ImpuestoCotizacionPageResponse listar(int page, int size);

    ImpuestoCotizacionResponse buscarPorId(Integer id);

    ImpuestoCotizacionResponse actualizar(
            Integer id,
            ImpuestoCotizacionRequest request);

    void eliminar(Integer id);
}
