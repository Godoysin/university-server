package com.luxoft.producer.service;

import com.luxoft.producer.model.Career;
import com.luxoft.producer.repository.CareerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogicService {

    @Autowired
    CareerRepository careerRepository;

    public List<String> getAllCareers() {
        log.info("Career list requested");
        return careerRepository.findAll().stream().map(Career::getCareerName).collect(Collectors.toList());
    }

}
