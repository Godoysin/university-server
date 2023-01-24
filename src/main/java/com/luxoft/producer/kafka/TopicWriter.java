package com.luxoft.producer.kafka;

import com.luxoft.messages.avro.TrackAvro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicWriter {

    @Autowired
    KafkaTemplate<Void, TrackAvro> kafkaTemplate;

    private static final String TOPIC = "track";

    private TopicWriter() {
    }

    public void send(String httpMethod, String resource) {
        TrackAvro trackAvro = new TrackAvro("noUser", httpMethod, resource, String.valueOf(System.currentTimeMillis()));

        sendToTopic(trackAvro);
    }


    public void sendToTopic(TrackAvro trackAvro) {
        kafkaTemplate.send(TOPIC, trackAvro);
    }

}
