package com.cuenta.moviemiento.microservicio.persistence.repository;

import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientosEntity, Long> {
    Optional<MovimientosEntity> findByFecha(final Date fecha);
}
