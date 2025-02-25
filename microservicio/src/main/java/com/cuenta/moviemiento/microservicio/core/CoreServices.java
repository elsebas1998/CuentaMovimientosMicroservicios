package com.cuenta.moviemiento.microservicio.core;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.Dto.EstadoCuentaDTO;
import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


public interface CoreServices {

    ResponseEntity<Object> crearCuenta(CuentaRequestDto cuentaRequestDto);

    ResponseEntity<Object> actualizarCuenta(String numeroCuenta, CuentaRequestDto cuentaRequestDto);

    ResponseEntity<Object> crearMovimiento(MovimientoRequestDto movimientoRequestDto);

    ResponseEntity<Object> actualizarMovimiento(Long id, MovimientoRequestDto movimientoRequestDto);

    ResponseEntity<Object> obtenerCuenta(String numeroCuenta);

    ResponseEntity<Object> obtenerMovimiento(Date fecha);

    ResponseEntity<Object> eliminarCuenta(String numeroCuenta);

    List<EstadoCuentaDTO> generarReporte(String clienteId, LocalDate fechaInicio, LocalDate fechaFin);
    ResponseEntity<Object> eliminarMovimiento(Long id);

    ResponseEntity<Object> crearMovimientoTransaccional(String numeroCuenta, BigDecimal valor);



}
