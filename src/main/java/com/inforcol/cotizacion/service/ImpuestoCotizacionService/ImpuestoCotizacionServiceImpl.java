package com.inforcol.cotizacion.service.ImpuestoCotizacionService;

import java.util.List;

import org.springframework.stereotype.Service;

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
    public List<ImpuestoCotizacionResponse> listarTodos() {
        log.info("Listando todos los impuestos de cotizacion");

        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
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
