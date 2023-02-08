package com.luxoft.producer.kafka.configuration;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.VoidSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfiguration {

    private static final String CLIENT_ID = "ex";

    @Value("${localhost.ip}")
    private String LOCALHOST_IP;

    @Value("${kafka.bootstrap.servers.port}")
    private String BOOTSTRAP_SERVERS_PORT;

    @Value("${kafka.schema.registry.port}")
    private String SCHEMA_REGISTRY_PORT;

    @Bean
    public ProducerFactory<Void, Object> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, LOCALHOST_IP + ":" + BOOTSTRAP_SERVERS_PORT);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put("schema.registry.url", "http://" + LOCALHOST_IP + ":" + SCHEMA_REGISTRY_PORT);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<Void, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
