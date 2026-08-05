package com.inforcol.cotizacion.dto.ImpuestoCotizacionDTO;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImpuestoCotizacionPageResponse {

    private List<ImpuestoCotizacionResponse> content;
    private long totalElements;
    private int totalPages;
    private int number;
    private int size;
    private boolean first;
    private boolean last;

    public static ImpuestoCotizacionPageResponse from(Page<ImpuestoCotizacionResponse> page) {
        return new ImpuestoCotizacionPageResponse(
                page.getContent(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getSize(),
                page.isFirst(),
                page.isLast());
    }
}
