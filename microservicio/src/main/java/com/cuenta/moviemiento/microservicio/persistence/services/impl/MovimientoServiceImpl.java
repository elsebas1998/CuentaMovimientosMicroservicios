package com.cuenta.moviemiento.microservicio.persistence.services.impl;

import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import com.cuenta.moviemiento.microservicio.persistence.repository.MovimientoRepository;
import com.cuenta.moviemiento.microservicio.persistence.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MovimientoServiceImpl implements MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public MovimientosEntity obtenerMovimiento(Date fecha) {
        return movimientoRepository.findByFecha(fecha)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }

    @Override
    public MovimientosEntity crearMovimiento(MovimientosEntity movimiento) {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public MovimientosEntity actualizarMovimiento(Long id, MovimientosEntity movimientoDetalles) {
        MovimientosEntity movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setFecha(movimientoDetalles.getFecha());
        movimiento.setTipoMovimiento(movimientoDetalles.getTipoMovimiento());
        movimiento.setValor(movimientoDetalles.getValor());
        movimiento.setSaldo(movimientoDetalles.getSaldo());
        return movimientoRepository.save(movimiento);
    }
}
