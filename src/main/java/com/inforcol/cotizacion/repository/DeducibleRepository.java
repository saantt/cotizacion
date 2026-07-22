package com.inforcol.cotizacion.repository;

import com.inforcol.cotizacion.model.Deducible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeducibleRepository extends JpaRepository<Deducible,Long> {

}
