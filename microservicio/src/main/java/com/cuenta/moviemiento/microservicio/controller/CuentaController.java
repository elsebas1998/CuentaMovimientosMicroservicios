package com.cuenta.moviemiento.microservicio.controller;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;
import com.cuenta.moviemiento.microservicio.persistence.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta/")
public class CuentaController {
    @Autowired
    private CuentaService cuentaService;

    @GetMapping("obtener/{numeroCuenta}")
    public ResponseEntity<CuentaEntity> obtenerCuenta(@PathVariable final String numeroCuenta) {
        return ResponseEntity.ok(cuentaService.obtenerCuenta(numeroCuenta));
    }

    @PostMapping("crear")
    public ResponseEntity<CuentaEntity> crearCuenta(@RequestBody final CuentaRequestDto cuentaRequestDto) {
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setNumeroCuenta(cuentaRequestDto.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaRequestDto.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaRequestDto.getSaldoInicial());
        cuenta.setEstado(cuentaRequestDto.getEstado());
        return ResponseEntity.ok(cuentaService.crearCuenta(cuenta));
    }

    @PutMapping("actualizar/{numeroCuenta}")
    public ResponseEntity<CuentaEntity> actualizarCuenta(@PathVariable final String numeroCuenta,
                                                         @RequestBody final CuentaRequestDto cuentaRequestDto) {
        CuentaEntity cuenta = new CuentaEntity();
        cuenta.setNumeroCuenta(cuentaRequestDto.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaRequestDto.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaRequestDto.getSaldoInicial());
        cuenta.setEstado(cuentaRequestDto.getEstado());
        return ResponseEntity.ok(cuentaService.actualizarCuenta(numeroCuenta, cuenta));
    }

    @DeleteMapping("eliminar/{numeroCuenta}")
    public ResponseEntity<Void> eliminarCuenta(@PathVariable final String numeroCuenta) {
        cuentaService.eliminarCuenta(numeroCuenta);
        return ResponseEntity.ok().build();
    }
}