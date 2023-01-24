package com.luxoft.producer.controller;

import com.luxoft.producer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("university/careers")
public class UniversityController {

    @Autowired
    LogicService logicService;

    @GetMapping("/career-list")
    public String careers() {
        return logicService.getCareers();
    }

}
