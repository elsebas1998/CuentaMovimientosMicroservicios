package com.cuenta.moviemiento.microservicio.persistence.services.impl;

import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;
import com.cuenta.moviemiento.microservicio.persistence.repository.CuentaRepository;
import com.cuenta.moviemiento.microservicio.persistence.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaServiceImpl implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public CuentaEntity obtenerCuenta(String numeroCuenta) {
        return cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    @Override
    public CuentaEntity crearCuenta(CuentaRequestDto cuenta) {
        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaEntity.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaEntity.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaEntity.setEstado(cuenta.getEstado());
        return cuentaRepository.save(cuentaEntity);
    }

    @Override
    public void eliminarCuenta(String numeroCuenta) {
        cuentaRepository.deleteByNumeroCuenta(numeroCuenta);
    }

    @Override
    public CuentaEntity actualizarCuenta(String numeroCuenta, CuentaRequestDto cuentaDetalles) {
        CuentaEntity cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setTipoCuenta(cuentaDetalles.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetalles.getSaldoInicial());
        cuenta.setEstado(cuentaDetalles.getEstado());
        return cuentaRepository.save(cuenta);
    }
}

