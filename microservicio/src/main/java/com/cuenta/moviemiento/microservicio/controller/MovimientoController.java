package com.cuenta.moviemiento.microservicio.controller;

import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.core.CoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/movimiento/")
public class MovimientoController {
    @Autowired
    private CoreServices coreServices;

    @GetMapping("/obtener/{fecha}")
    public ResponseEntity<Object> obtenerMovimiento(@PathVariable final Date fecha) {
        return coreServices.obtenerMovimiento(fecha);
    }

    @PostMapping("/crear")
    public ResponseEntity<Object> crearMovimiento(@RequestBody final MovimientoRequestDto movimientoRequestDto) {
        return coreServices.crearMovimiento(movimientoRequestDto);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Object> actualizarMovimiento(@PathVariable final Long id,
                                                                 @RequestBody final MovimientoRequestDto movimientoRequestDto) {
        return coreServices.actualizarMovimiento(id, movimientoRequestDto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Object> eliminarMovimiento(@PathVariable final Long id) {
        return eliminarMovimiento(id);
    }
}
