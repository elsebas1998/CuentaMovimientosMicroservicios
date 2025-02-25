package com.cuenta.moviemiento.microservicio.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TOPIC_CLIENTE_REQUEST = "cliente-info-request";
    public static final String TOPIC_CLIENTE_RESPONSE = "cliente-info-response";


    @Bean
    public NewTopic clienteRequestTopic() {
        return TopicBuilder.name(TOPIC_CLIENTE_REQUEST)
                .partitions(3)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic clienteResponseTopic() {
        return TopicBuilder.name(TOPIC_CLIENTE_RESPONSE)
                .partitions(3)
                .replicas(1)
                .build();
    }
}