package com.cuenta.moviemiento.microservicio.persistence.repository;

import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
    Optional<CuentaEntity> findByNumeroCuenta(final String numeroCuenta);
    void deleteByNumeroCuenta(final String numeroCuenta);
}
