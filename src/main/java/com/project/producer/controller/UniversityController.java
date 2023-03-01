package com.project.producer.controller;

import com.project.producer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("university/careers")
public class UniversityController {

    @Autowired
    LogicService logicService;

    @GetMapping("/career-list")
    public List<String> careers() {
        return logicService.getAllCareers();
    }

}
