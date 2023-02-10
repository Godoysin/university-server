package com.luxoft.producer.kafka;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TopicWriterTest {

    @InjectMocks
    private TopicWriter topicWriterMock;

    @Mock
    private KafkaTemplate<Void, Object> kafkaTemplateMock;

    private static final String message = "message";

    @Test
    public void shouldSendToContactTopicUsingKafkaTemplateSend() {
        // given

        // when
        topicWriterMock.sendToContactTopic(message);

        // then
        verify(kafkaTemplateMock).send(anyString(), eq(message));
    }

    @Test
    public void shouldSendToErrorTopicUsingKafkaTemplateSend() {
        // given

        // when
        topicWriterMock.sendToErrorTopic(message);

        // then
        verify(kafkaTemplateMock).send(anyString(), eq(message));
    }

    @Test
    public void shouldSendToTrackTopicUsingKafkaTemplateSend() {
        // given
        String httpMethod = "GET";
        String resource = "/resource";

        // when
        topicWriterMock.trackTopicSender(httpMethod, resource);

        // then
        verify(kafkaTemplateMock).send(anyString(), any());
    }

}
