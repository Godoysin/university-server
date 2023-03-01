package com.project.producer.kafka;

import com.project.messages.avro.TrackAvro;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicWriter {

    private final KafkaTemplate<Void, Object> kafkaTemplate;

    protected static final String CONTACT_TOPIC = "contact";
    protected static final String ERROR_TOPIC = "error";
    protected static final String TRACK_TOPIC = "track";

    @Autowired
    public TopicWriter(KafkaTemplate<Void, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void trackTopicSender(String httpMethod, String resource) {
        TrackAvro trackAvro = new TrackAvro("noUser", httpMethod, resource, new DateTime(System.currentTimeMillis()));

        sendToTrackTopic(trackAvro);
    }

    public void sendToContactTopic(String stringMessage) {
        kafkaTemplate.send(CONTACT_TOPIC, stringMessage);
    }

    public void sendToErrorTopic(String stringMessage) {
        kafkaTemplate.send(ERROR_TOPIC, stringMessage);
    }

    private void sendToTrackTopic(TrackAvro trackAvroMessage) {
        kafkaTemplate.send(TRACK_TOPIC, trackAvroMessage);
    }

}
