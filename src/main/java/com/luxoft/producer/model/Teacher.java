package com.luxoft.producer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    private String teacherName;

    private Teacher() {
    }

    public Teacher(String teacherName) {
        setTeacherName(teacherName);
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

}
