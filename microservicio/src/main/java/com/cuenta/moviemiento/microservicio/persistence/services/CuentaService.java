package com.cuenta.moviemiento.microservicio.persistence.services;


import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;

public interface CuentaService {
    CuentaEntity obtenerCuenta(String numeroCuenta);
    CuentaEntity crearCuenta(CuentaEntity cuenta);
    void eliminarCuenta(String numeroCuenta);
    CuentaEntity actualizarCuenta(String numeroCuenta, CuentaEntity cuentaDetalles);
}

