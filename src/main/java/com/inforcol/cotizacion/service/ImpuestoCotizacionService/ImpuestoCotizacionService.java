package com.inforcol.cotizacion.service.ImpuestoCotizacionService;

import java.util.List;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;

public interface ImpuestoCotizacionService {

    ImpuestoCotizacionResponse crear(ImpuestoCotizacionRequest request);

    List<ImpuestoCotizacionResponse> listarTodos();

    ImpuestoCotizacionResponse buscarPorId(Integer id);

    ImpuestoCotizacionResponse actualizar(
            Integer id,
            ImpuestoCotizacionRequest request);

    void eliminar(Integer id);
}
