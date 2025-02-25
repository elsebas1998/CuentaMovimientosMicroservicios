package com.cuenta.moviemiento.microservicio.persistence.services.impl;

import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import com.cuenta.moviemiento.microservicio.persistence.repository.MovimientoRepository;
import com.cuenta.moviemiento.microservicio.persistence.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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
    public MovimientosEntity crearMovimiento(MovimientoRequestDto movimiento) {
            MovimientosEntity movimientosEntity = new MovimientosEntity();
            movimientosEntity.setFecha(movimiento.getFecha());
            movimientosEntity.setSaldo(movimiento.getSaldo());
            movimientosEntity.setValor(movimiento.getValor());
            movimientosEntity.setTipoMovimiento(movimiento.getTipoMovimiento());
            movimientosEntity.setCodCuenta(movimiento.getCodCuenta());
            return movimientoRepository.save(movimientosEntity);
    }

    @Override
    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }

    @Override
    public MovimientosEntity actualizarMovimiento(Long id, MovimientoRequestDto movimientoDetalles) {
        MovimientosEntity movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        movimiento.setFecha(movimientoDetalles.getFecha());
        movimiento.setTipoMovimiento(movimientoDetalles.getTipoMovimiento());
        movimiento.setValor(movimientoDetalles.getValor());
        movimiento.setSaldo(movimientoDetalles.getSaldo());
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<MovimientosEntity> obtenerMovimientos(Integer codCuenta, LocalDate fechaInicio, LocalDate fechaFin) {
        return movimientoRepository.findByCodCuentaAndFechaBetween(codCuenta, fechaInicio, fechaFin);
    }
}
