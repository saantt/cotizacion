package com.inforcol.cotizacion.service.ImpuestoCotizacionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionPageResponse;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO.ImpuestoCotizacionResponse;
import com.inforcol.cotizacion.model.ImpuestoCotizacion.ImpuestoCotizacion;
import com.inforcol.cotizacion.repository.ImpuestoCotizacionRepository.ImpuestoCotizacionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImpuestoCotizacionServiceImpl implements ImpuestoCotizacionService {

    private final ImpuestoCotizacionRepository repository;

    @Override
    public ImpuestoCotizacionResponse crear(ImpuestoCotizacionRequest request) {
        log.info("Creando impuesto de cotizacion");

        ImpuestoCotizacion impuesto = new ImpuestoCotizacion();
        impuesto.setIdCotizacion(request.getIdCotizacion());
        impuesto.setConcepto(request.getConcepto());
        impuesto.setValor(request.getValor());

        ImpuestoCotizacion impuestoGuardado = repository.save(impuesto);
        return convertirAResponse(impuestoGuardado);
    }

    @Override
    public ImpuestoCotizacionPageResponse listar(int page, int size) {
        log.info("Listando impuestos de cotizacion. Pagina: {}, tamano: {}", page, size);

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.by(Sort.Direction.ASC, "idImpuestoCot"));
        Page<ImpuestoCotizacionResponse> resultado = repository.findAll(pageRequest)
                .map(this::convertirAResponse);

        return ImpuestoCotizacionPageResponse.from(resultado);
    }

    @Override
    public ImpuestoCotizacionResponse buscarPorId(Integer id) {
        log.info("Buscando impuesto de cotizacion con id: {}", id);

        ImpuestoCotizacion impuesto = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No se encontró el impuesto con id: " + id));

        return convertirAResponse(impuesto);
    }

    @Override
    public ImpuestoCotizacionResponse actualizar(
            Integer id,
            ImpuestoCotizacionRequest request) {
        log.info("Actualizando impuesto de cotizacion con id: {}", id);

        ImpuestoCotizacion impuesto = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No se encontró el impuesto con id: " + id));

        impuesto.setIdCotizacion(request.getIdCotizacion());
        impuesto.setConcepto(request.getConcepto());
        impuesto.setValor(request.getValor());

        ImpuestoCotizacion impuestoActualizado = repository.save(impuesto);
        return convertirAResponse(impuestoActualizado);
    }

    @Override
    public void eliminar(Integer id) {
        log.info("Eliminando impuesto de cotizacion con id: {}", id);

        ImpuestoCotizacion impuesto = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No se encontró el impuesto con id: " + id));

        repository.delete(impuesto);
    }

    private ImpuestoCotizacionResponse convertirAResponse(
            ImpuestoCotizacion impuesto) {
        return new ImpuestoCotizacionResponse(
                impuesto.getIdImpuestoCot(),
                impuesto.getIdCotizacion(),
                impuesto.getConcepto(),
                impuesto.getValor());
    }
}
