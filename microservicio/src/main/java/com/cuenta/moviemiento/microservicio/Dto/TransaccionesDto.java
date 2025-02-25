package com.cuenta.moviemiento.microservicio.Dto;

import java.math.BigDecimal;

public class TransaccionesDto {

    private String numeroCuenta;
    private BigDecimal valor;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
