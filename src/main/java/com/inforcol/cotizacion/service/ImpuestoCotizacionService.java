package com.inforcol.cotizacion.service;

import com.inforcol.cotizacion.DTO.ImpuestoCotizacionRequest;
import com.inforcol.cotizacion.DTO.ImpuestoCotizacionResponse;
import com.inforcol.cotizacion.model.ImpuestoCotizacion;
import com.inforcol.cotizacion.repository.ImpuestoCotizacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpuestoCotizacionService {
    private final ImpuestoCotizacionRepository repository;

    public ImpuestoCotizacionService(ImpuestoCotizacionRepository repository) {
        this.repository = repository;
    }

    public ImpuestoCotizacionResponse crear(ImpuestoCotizacionRequest request) {
        ImpuestoCotizacion impuesto = new ImpuestoCotizacion();
        impuesto.setIdCotizacion(request.getIdCotizacion());
        impuesto.setConcepto(request.getConcepto());
        impuesto.setValor(request.getValor());

        ImpuestoCotizacion impuestoGuardado = repository.save(impuesto);
        return convertirAResponse(impuestoGuardado);
    }

    public List<ImpuestoCotizacionResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::convertirAResponse)
                .toList();
    }

    public ImpuestoCotizacionResponse buscarPorId(Integer id) {
        ImpuestoCotizacion impuesto = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con id: " + id));
        return convertirAResponse(impuesto);
    }

    public ImpuestoCotizacionResponse actualizar(Integer id, ImpuestoCotizacionRequest request) {
        ImpuestoCotizacion impuesto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con id: " + id));

        impuesto.setIdCotizacion(request.getIdCotizacion());
        impuesto.setConcepto(request.getConcepto());
        impuesto.setValor(request.getValor());

        ImpuestoCotizacion impuestoActualizado = repository.save(impuesto);
        return convertirAResponse(impuestoActualizado);
    }

    public void eliminar(Integer id) {
        ImpuestoCotizacion impuesto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró el impuesto con id: " + id));
        repository.delete(impuesto);
    }

    private ImpuestoCotizacionResponse convertirAResponse(ImpuestoCotizacion impuesto) {
        return new ImpuestoCotizacionResponse(
                impuesto.getIdImpuestoCot(),
                impuesto.getIdCotizacion(),
                impuesto.getConcepto(),
                impuesto.getValor()
        );
    }
}