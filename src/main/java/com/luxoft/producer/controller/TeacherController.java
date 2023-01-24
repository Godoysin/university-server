package com.luxoft.producer.controller;

import com.luxoft.producer.model.Teacher;
import com.luxoft.producer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("university/teachers")
public class TeacherController {

    @Autowired
    LogicService logicService;

    @GetMapping("/teacher-list")
    public List<Teacher> teachers(@RequestParam String careerName) {
        return logicService.get(careerName);
    }

}
