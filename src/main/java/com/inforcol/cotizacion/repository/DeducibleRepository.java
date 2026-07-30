package com.inforcol.cotizacion.repository;

import com.inforcol.cotizacion.model.Deducible;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeducibleRepository extends JpaRepository<Deducible, Long> {

    List<Deducible> findByPorcentaje(Integer porcentaje);

    List<Deducible> findByPorcentajeGreaterThan(Integer porcentaje);

    List<Deducible> findByMontoMinimoGreaterThan(Double monto);

    List<Deducible> findByPorcentajeBetween(Integer inicio, Integer fin);

    boolean existsByPorcentaje(Integer porcentaje);

    boolean existsByPorcentajeAndMontoMinimo(Integer porcentaje,
                                             Double montoMinimo);

}
