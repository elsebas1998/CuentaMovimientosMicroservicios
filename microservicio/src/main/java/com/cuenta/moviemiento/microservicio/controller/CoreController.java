package com.cuenta.moviemiento.microservicio.controller;

import com.cuenta.moviemiento.microservicio.Dto.EstadoCuentaDTO;
import com.cuenta.moviemiento.microservicio.Dto.TransaccionesDto;
import com.cuenta.moviemiento.microservicio.config.SaldoNoDisponibleException;
import com.cuenta.moviemiento.microservicio.core.CoreServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/operaciones")
public class CoreController {
    @Autowired
    private CoreServices coreServices;
    @GetMapping("/reportes")
    public ResponseEntity<List<EstadoCuentaDTO>> generarReporte(
            @RequestParam String identificacion,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin) {

        List<EstadoCuentaDTO> reporte = coreServices.generarReporte(identificacion, fechaInicio, fechaFin);
        return ResponseEntity.ok(reporte);
    }

    @PostMapping("/movimientos")
    public ResponseEntity<Object> create(@Valid @RequestBody TransaccionesDto request) {
            return coreServices.crearMovimientoTransaccional(request.getNumeroCuenta(), request.getValor());

    }
}
