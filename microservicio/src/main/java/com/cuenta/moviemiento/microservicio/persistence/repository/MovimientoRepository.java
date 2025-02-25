package com.cuenta.moviemiento.microservicio.persistence.repository;

import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientosEntity, Long> {
    Optional<MovimientosEntity> findByFecha(final Date fecha);
    List<MovimientosEntity> findByCodCuentaAndFechaBetween(Integer codCuenta, LocalDate fechaInicio, LocalDate fechaFin);

}
