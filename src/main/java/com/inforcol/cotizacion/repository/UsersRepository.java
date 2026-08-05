package com.inforcol.cotizacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inforcol.cotizacion.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
