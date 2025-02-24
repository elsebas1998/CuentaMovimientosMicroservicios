package com.cuenta.moviemiento.microservicio.controller;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.core.CoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta/")
public class CuentaController {
    @Autowired
    private CoreServices coreServices;

    @GetMapping("obtener/{numeroCuenta}")
    public ResponseEntity<Object> obtenerCuenta(@PathVariable final String numeroCuenta) {
        return coreServices.obtenerCuenta(numeroCuenta);
    }

    @PostMapping("crear")
    public ResponseEntity<Object> crearCuenta(@RequestBody final CuentaRequestDto cuentaRequestDto) {
        return coreServices.crearCuenta(cuentaRequestDto);
    }

    @PutMapping("actualizar/{numeroCuenta}")
    public ResponseEntity<Object> actualizarCuenta(@PathVariable final String numeroCuenta,
                                                         @RequestBody final CuentaRequestDto cuentaRequestDto) {
        return coreServices.actualizarCuenta(numeroCuenta, cuentaRequestDto);
    }

    @DeleteMapping("eliminar/{numeroCuenta}")
    public ResponseEntity<Object> eliminarCuenta(@PathVariable final String numeroCuenta) {
        return coreServices.eliminarCuenta(numeroCuenta);
    }
}