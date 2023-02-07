package com.luxoft.producer.service;

import com.google.gson.Gson;
import com.luxoft.producer.kafka.TopicWriter;
import com.luxoft.producer.db.model.Career;
import com.luxoft.producer.db.model.Teacher;
import com.luxoft.producer.db.repository.CareerRepository;
import com.luxoft.producer.db.repository.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LogicService {

    @Autowired
    CareerRepository careerRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    TopicWriter topicWriter;

    private final Gson gson = new Gson();

    public List<String> getAllCareers() {
        log.info("Career list requested");
        return careerRepository.findAll().stream().map(Career::getCareerName).collect(Collectors.toList());
    }

    public List<Teacher> getTeachersByCareer(String careerName) {
        log.info("All teachers requested by career name: {}", careerName);
        return teacherRepository.findTeachersByCareerName(careerName);
    }

    public String requestTeacherContact(Teacher teacher) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacher.getTeacherName());

        if (optionalTeacher.isPresent()) {
            log.info("To contact teacher topic");
            topicWriter.sendToContactTopic("test topic message");
        }
        else {
            log.error("To error topic");
            topicWriter.sendToErrorTopic(gson.toJson(teacher));
        }

        return "test message";
    }

}
