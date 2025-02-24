package com.cuenta.moviemiento.microservicio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CuentaRequestDto {
    @NotNull
    private String numeroCuenta;
    @NotNull
    private String tipoCuenta;
    @NotNull
    private Double saldoInicial;
    @NotNull
    private String estado;
}
