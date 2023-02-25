package com.como.hogar.data.repositories;

import com.como.hogar.data.entities.cliente.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Page<Cliente> findAllByEstado(Pageable pageable, String estado);

    Optional<Cliente> findByNombre(String nombre);

    Optional<Cliente> findByNombreOrEmail(String nombre, String email);
}
