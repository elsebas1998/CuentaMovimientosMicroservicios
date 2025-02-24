package com.cuenta.moviemiento.microservicio.persistence.services;

import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;

import java.util.Date;

public interface MovimientoService {
    MovimientosEntity obtenerMovimiento(Date fecha);
    MovimientosEntity crearMovimiento(MovimientosEntity movimiento);
    void eliminarMovimiento(Long id);
    MovimientosEntity actualizarMovimiento(Long id, MovimientosEntity movimientoDetalles);
}
