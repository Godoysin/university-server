package com.luxoft.producer.controller.filter;

import com.luxoft.producer.kafka.TopicWriter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Integer.MAX_VALUE)
@Slf4j
public class RequestTrack implements Filter {

    @Autowired
    TopicWriter topicWriter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Request received from: {} {}", req.getMethod(), req.getRequestURI());
        topicWriter.trackTopicSender(req.getMethod(), req.getRequestURI());
        chain.doFilter(request, response);
    }

}
