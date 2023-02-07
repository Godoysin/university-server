package com.luxoft.producer.controller;

import com.luxoft.producer.db.model.Teacher;
import com.luxoft.producer.service.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("university/teachers")
public class TeacherController {

    @Autowired
    LogicService logicService;

    @GetMapping("/teacher-list")
    public List<Teacher> getTeachersByCareer(@RequestParam @NonNull String careerName) {
        return logicService.getTeachersByCareer(careerName);
    }

    @PostMapping("/contact-request")
    public String requestTeacherContact(@RequestBody @NonNull Teacher teacher) {
        return logicService.requestTeacherContact(teacher);
    }

}
