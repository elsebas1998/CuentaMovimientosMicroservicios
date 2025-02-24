package com.cuenta.moviemiento.microservicio.controller;

import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import com.cuenta.moviemiento.microservicio.persistence.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/movimiento/")
public class MovimientoController {
    @Autowired
    private MovimientoService movimientoService;

    @GetMapping("obtener/{fecha}")
    public ResponseEntity<MovimientosEntity> obtenerMovimiento(@PathVariable final Date fecha) {
        return ResponseEntity.ok(movimientoService.obtenerMovimiento(fecha));
    }

    @PostMapping("crear")
    public ResponseEntity<MovimientosEntity> crearMovimiento(@RequestBody final MovimientoRequestDto movimientoRequestDto) {
        MovimientosEntity movimiento = new MovimientosEntity();
        movimiento.setFecha(movimientoRequestDto.getFecha());
        movimiento.setTipoMovimiento(movimientoRequestDto.getTipoMovimiento());
        movimiento.setValor(movimientoRequestDto.getValor());
        movimiento.setSaldo(movimientoRequestDto.getSaldo());
        return ResponseEntity.ok(movimientoService.crearMovimiento(movimiento));
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<MovimientosEntity> actualizarMovimiento(@PathVariable final Long id,
                                                                 @RequestBody final MovimientoRequestDto movimientoRequestDto) {
        MovimientosEntity movimiento = new MovimientosEntity();
        movimiento.setFecha(movimientoRequestDto.getFecha());
        movimiento.setTipoMovimiento(movimientoRequestDto.getTipoMovimiento());
        movimiento.setValor(movimientoRequestDto.getValor());
        movimiento.setSaldo(movimientoRequestDto.getSaldo());
        return ResponseEntity.ok(movimientoService.actualizarMovimiento(id, movimiento));
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable final Long id) {
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.ok().build();
    }
}
