package com.cuenta.moviemiento.microservicio.core;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;


public interface CoreServices {

    ResponseEntity<Object> crearCuenta(CuentaRequestDto cuentaRequestDto);

    ResponseEntity<Object> actualizarCuenta(String numeroCuenta, CuentaRequestDto cuentaRequestDto);

    ResponseEntity<Object> crearMovimiento(MovimientoRequestDto movimientoRequestDto);

    ResponseEntity<Object> actualizarMovimiento(Long id, MovimientoRequestDto movimientoRequestDto);

    ResponseEntity<Object> obtenerCuenta(String numeroCuenta);

    ResponseEntity<Object> obtenerMovimiento(Date fecha);

    ResponseEntity<Object> eliminarCuenta(String numeroCuenta);

    ResponseEntity<Object> eliminarMovimiento(Long id);



}
