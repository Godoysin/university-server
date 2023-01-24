package com.luxoft.producer.service;

import com.luxoft.producer.kafka.TopicWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogicService {

    @Autowired
    TopicWriter topicWriter;

    public String getCareers() {

        log.info("Career list requested");

        return "test";
    }

}
