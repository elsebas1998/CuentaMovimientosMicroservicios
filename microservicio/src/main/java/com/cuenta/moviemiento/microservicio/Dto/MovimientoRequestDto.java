package com.cuenta.moviemiento.microservicio.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoRequestDto {
    @NotNull
    private Date fecha;
    @NotNull
    private String tipoMovimiento;
    @NotNull
    private Double valor;
    @NotNull
    private Double saldo;
}
