package com.cuenta.moviemiento.microservicio.Dto;

import java.util.UUID;

public class ClienteInfoRequest {
    private String requestId;
    private String clienteId;
    private String cuentaId;

    public ClienteInfoRequest() {
        this.requestId = UUID.randomUUID().toString();
    }

    public ClienteInfoRequest(String clienteId, String cuentaId) {
        this();
        this.clienteId = clienteId;
        this.cuentaId = cuentaId;
    }

    // Getters y setters
    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(String cuentaId) {
        this.cuentaId = cuentaId;
    }
}
