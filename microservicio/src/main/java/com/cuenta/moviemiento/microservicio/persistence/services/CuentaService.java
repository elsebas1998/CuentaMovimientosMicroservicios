package com.cuenta.moviemiento.microservicio.persistence.services;


import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;

import java.util.List;

public interface CuentaService {
    CuentaEntity obtenerCuenta(String numeroCuenta);
    CuentaEntity crearCuenta(CuentaRequestDto cuenta);
    void eliminarCuenta(String numeroCuenta);
    CuentaEntity actualizarCuenta(String numeroCuenta, CuentaRequestDto cuentaDetalles);

    List<CuentaEntity> obtenerCuentasCliente(String identificacion);
}

