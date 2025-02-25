package com.cuenta.moviemiento.microservicio.core.impl;

import com.cuenta.moviemiento.microservicio.Dto.ClienteInfoResponse;
import com.cuenta.moviemiento.microservicio.Dto.CuentaRequestDto;
import com.cuenta.moviemiento.microservicio.Dto.EstadoCuentaDTO;
import com.cuenta.moviemiento.microservicio.Dto.MovimientoRequestDto;
import com.cuenta.moviemiento.microservicio.config.SaldoNoDisponibleException;
import com.cuenta.moviemiento.microservicio.core.CoreServices;
import com.cuenta.moviemiento.microservicio.persistence.entities.CuentaEntity;
import com.cuenta.moviemiento.microservicio.persistence.entities.MovimientosEntity;
import com.cuenta.moviemiento.microservicio.persistence.entities.PersonaEntity;
import com.cuenta.moviemiento.microservicio.persistence.services.CuentaService;
import com.cuenta.moviemiento.microservicio.persistence.services.MovimientoService;
import com.cuenta.moviemiento.microservicio.persistence.services.PersonaService;
import com.cuenta.moviemiento.microservicio.persistence.services.impl.ClienteKafkaServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

@Service
public class CoreServicesImpl implements CoreServices {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private ClienteKafkaServiceImpl clienteKafkaService;

    @Autowired
    private PersonaService personaService;

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
            personaService.obtenerPersona(cuentaRequestDto.getIdentificacionCliente());
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


    /**
     * Crea un nuevo movimiento en la cuenta especificada
     */
    @Transactional
    public ResponseEntity<Object> crearMovimientoTransaccional(String numeroCuenta, BigDecimal valor) {

        try {
            CuentaEntity cuenta = cuentaService.obtenerCuenta(numeroCuenta);

            BigDecimal saldoActual = cuenta.getSaldoInicial();

            if (valor.compareTo(BigDecimal.ZERO) < 0 && saldoActual.add(valor).compareTo(BigDecimal.ZERO) < 0) {
                throw new SaldoNoDisponibleException("Saldo no disponible");
            }

            BigDecimal nuevoSaldo = saldoActual.add(valor);

            cuenta.setSaldoInicial(nuevoSaldo);
            CuentaRequestDto cuentaRequestDto = new CuentaRequestDto();
            cuentaRequestDto.setEstado(cuenta.getEstado());
            cuentaRequestDto.setSaldoInicial(cuenta.getSaldoInicial());
            cuentaRequestDto.setTipoCuenta(cuenta.getTipoCuenta());
            cuentaRequestDto.setNumeroCuenta(cuenta.getNumeroCuenta());
            cuentaService.actualizarCuenta(numeroCuenta, cuentaRequestDto);


            MovimientoRequestDto movimiento = new MovimientoRequestDto();
            Integer codCuenta = Math.toIntExact(cuenta.getId());
            movimiento.setCodCuenta(codCuenta);
            movimiento.setFecha(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            movimiento.setTipoMovimiento(valor.compareTo(BigDecimal.ZERO) > 0 ? "Deposito" : "Retiro");
            movimiento.setValor(valor);
            movimiento.setSaldo(nuevoSaldo);
            movimientoService.crearMovimiento(movimiento);
            return ResponseEntity.status(HttpStatus.OK).body("Se realizo un movimiento de forma exitosa");
        } catch (Exception e) {
            return buildResponseError(e.getMessage());
        }

    }

    /**
     * Genera un reporte de estado de cuenta para un cliente y rango de fechas
     */
    @Override
    public List<EstadoCuentaDTO> generarReporte(String identificacion, LocalDate fechaInicio, LocalDate fechaFin) {
        CompletableFuture<ClienteInfoResponse> clienteFuture =
                clienteKafkaService.requestClienteInfo(identificacion, null);

        try {
            ClienteInfoResponse clienteInfo = clienteFuture.get(5, TimeUnit.SECONDS);

            List<CuentaEntity> cuentas = cuentaService.obtenerCuentasCliente(identificacion);

            return cuentas.stream()
                    .flatMap(cuenta -> {

                        Integer codCuenta = Math.toIntExact(cuenta.getId());

                        return movimientoService.obtenerMovimientos(codCuenta, fechaInicio, fechaFin)
                                .stream()
                                .map(movimiento -> {
                                    EstadoCuentaDTO dto = new EstadoCuentaDTO();
                                    dto.setFecha(movimiento.getFecha());
                                    dto.setCliente(clienteInfo.getNombre());
                                    dto.setNumeroCuenta(cuenta.getNumeroCuenta());
                                    dto.setTipo(cuenta.getTipoCuenta());
                                    dto.setSaldoInicial(cuenta.getSaldoInicial().subtract(movimiento.getValor()));
                                    dto.setEstado(cuenta.getEstado());
                                    dto.setMovimiento(movimiento.getValor());
                                    dto.setSaldoDisponible(movimiento.getSaldo());
                                    return dto;
                                });
                    })
                    .collect(Collectors.toList());

        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Error al obtener información del cliente: " + e.getMessage(), e);
        }
    }
}
