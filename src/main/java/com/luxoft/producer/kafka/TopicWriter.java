package com.luxoft.producer.kafka;

import com.luxoft.messages.avro.TrackAvro;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TopicWriter {

    @Autowired
    KafkaTemplate<Void, Object> kafkaTemplate;

    private static final String CONTACT_TOPIC = "contact";
    private static final String ERROR_TOPIC = "error";
    private static final String TRACK_TOPIC = "track";

    public TopicWriter() {
    }

    public void trackTopicSender(String httpMethod, String resource) {
        TrackAvro trackAvro = new TrackAvro("noUser", httpMethod, resource, new DateTime(System.currentTimeMillis()));

        sendToTrackTopic(trackAvro);
    }

    public void sendToContactTopic(String string) {
        kafkaTemplate.send(CONTACT_TOPIC, string);
    }

    public void sendToErrorTopic(String string) {
        kafkaTemplate.send(ERROR_TOPIC, string);
    }

    private void sendToTrackTopic(TrackAvro trackAvro) {
        kafkaTemplate.send(TRACK_TOPIC, trackAvro);
    }

}
