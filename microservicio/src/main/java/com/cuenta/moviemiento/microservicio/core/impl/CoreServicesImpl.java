package com.cuenta.moviemiento.microservicio.core.impl;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.core.CoreServices;
import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;
import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import com.cuenta.moviemiento.microservicio.persistence.services.CuentaService;
import com.cuenta.moviemiento.microservicio.persistence.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CoreServicesImpl implements CoreServices {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    private ResponseEntity<Object> buildResponseError(final String detalle) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Error");
        response.put("detalle", detalle);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private ResponseEntity<Object> obtenerCreacionResponse(final Object data) {
        Map<String, Object> response = new HashMap<>();
        response.put("Data", data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Object> crearCuenta(CuentaRequestDto cuentaRequestDto) {
        try {
            cuentaService.crearCuenta(cuentaRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Se creo la cuenta de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarCuenta(String numeroCuenta, CuentaRequestDto cuentaRequestDto) {
        try {
            cuentaService.actualizarCuenta(numeroCuenta, cuentaRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo la cuenta de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> crearMovimiento(MovimientoRequestDto movimientoRequestDto) {
        try {
            movimientoService.crearMovimiento(movimientoRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se creo el movimiento de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> actualizarMovimiento(Long id, MovimientoRequestDto movimientoRequestDto) {
        try {
            movimientoService.actualizarMovimiento(id, movimientoRequestDto);
            return ResponseEntity.status(HttpStatus.OK).body("Se actualizo el movimiento de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerCuenta(String numeroCuenta) {
        try {
            CuentaEntity cuenta = cuentaService.obtenerCuenta(numeroCuenta);
            return obtenerCreacionResponse(cuenta);
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> obtenerMovimiento(Date fecha) {
        try {
            MovimientosEntity movimientos = movimientoService.obtenerMovimiento(fecha);
            return obtenerCreacionResponse(movimientos);
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> eliminarCuenta(String numeroCuenta) {
        try {
            cuentaService.eliminarCuenta(numeroCuenta);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino la cuenta de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> eliminarMovimiento(Long id) {
        try {
            movimientoService.eliminarMovimiento(id);
            return ResponseEntity.status(HttpStatus.OK).body("Se elimino el movimiento de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }
    }
}
