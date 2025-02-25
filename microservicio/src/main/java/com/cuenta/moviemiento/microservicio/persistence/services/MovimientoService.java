package com.cuenta.moviemiento.microservicio.persistence.services;

import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface MovimientoService {
    MovimientosEntity obtenerMovimiento(Date fecha);
    MovimientosEntity crearMovimiento(MovimientoRequestDto movimiento);
    void eliminarMovimiento(Long id);
    MovimientosEntity actualizarMovimiento(Long id, MovimientoRequestDto movimientoDetalles);

    List<MovimientosEntity> obtenerMovimientos(Integer codCuenta, LocalDate fechaInicio, LocalDate fechaFin);
}
