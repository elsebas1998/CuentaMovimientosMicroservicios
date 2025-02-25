package com.cuenta.moviemiento.microservicio.persistence.services.impl;


import com.cuenta.moviemiento.microservicio.Dto.ClienteInfoRequest;
import com.cuenta.moviemiento.microservicio.Dto.ClienteInfoResponse;
import com.cuenta.moviemiento.microservicio.config.KafkaTopicConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ClienteKafkaServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ClienteKafkaServiceImpl.class);

    @Autowired
    private KafkaTemplate<String, ClienteInfoRequest> kafkaTemplate;


    private final Map<String, CompletableFuture<ClienteInfoResponse>> pendingRequests = new ConcurrentHashMap<>();

    /**
     * Envía una solicitud al microservicio de clientes para obtener información
     */
    public CompletableFuture<ClienteInfoResponse> requestClienteInfo(String clienteId, String cuentaId) {
        ClienteInfoRequest request = new ClienteInfoRequest(clienteId, cuentaId);
        CompletableFuture<ClienteInfoResponse> future = new CompletableFuture<>();

        pendingRequests.put(request.getRequestId(), future);

        CompletableFuture<SendResult<String, ClienteInfoRequest>> sendResult =
                kafkaTemplate.send(KafkaTopicConfig.TOPIC_CLIENTE_REQUEST, clienteId, request);

        sendResult.whenComplete((result, ex) -> {
            if (ex != null) {
                pendingRequests.remove(request.getRequestId());
                future.completeExceptionally(ex);
                logger.error("Error enviando solicitud de información de cliente: {}", ex.getMessage());
            } else {
                logger.info("Solicitud de información enviada para clienteId: {}", clienteId);
            }
        });

        return future;
    }

    /**
     * Recibe las respuestas del microservicio de clientes
     */
    @KafkaListener(topics = KafkaTopicConfig.TOPIC_CLIENTE_RESPONSE,  groupId = "mi-grupo-productor")
    public void handleClienteInfoResponse(ClienteInfoResponse response) {
        logger.info("Respuesta recibida para requestId: {}", response.getRequestId());

        CompletableFuture<ClienteInfoResponse> future = pendingRequests.remove(response.getRequestId());
        if (future != null) {
            future.complete(response);
        } else {
            logger.warn("Recibida respuesta para una solicitud desconocida: {}", response.getRequestId());
        }
    }
}
