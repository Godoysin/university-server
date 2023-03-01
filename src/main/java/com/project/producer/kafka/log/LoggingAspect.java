package com.project.producer.kafka.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingAspect {

    @Before("execution(* com.project.producer.kafka.TopicWriter.sendTo*(..))")
    public void print(JoinPoint joinPoint) {
        log.info("Sending to Kafka using {} with arguments: {}", joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

}
