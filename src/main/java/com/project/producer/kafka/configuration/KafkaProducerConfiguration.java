package com.project.producer.kafka.configuration;

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

    @Value("${kafka.ip}")
    private String kafkaIp;

    @Value("${kafka.bootstrap.servers.port}")
    private String bootstrapServersPort;

    @Value("${kafka.schema.registry.port}")
    private String schemaRegistryPort;


    @Bean
    public ProducerFactory<Void, Object> producerFactory() {
        Map<String, Object> properties = new HashMap<>();

        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaIp + ":" + bootstrapServersPort);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, VoidSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        properties.put("schema.registry.url", "http://" + kafkaIp + ":" + schemaRegistryPort);

        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<Void, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

}
