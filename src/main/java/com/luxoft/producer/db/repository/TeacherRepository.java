package com.luxoft.producer.db.repository;

import com.luxoft.producer.db.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    @Query(value = "SELECT teacher.teacher_name FROM teacher JOIN subject_teacher ON teacher.teacher_name = subject_teacher.teacher_name WHERE subject_teacher.subject_name IN (SELECT DISTINCT career_subject.subject_name FROM career_subject JOIN subject_teacher ON career_subject.subject_name = subject_teacher.subject_name WHERE career_subject.career_name = ?1)", nativeQuery = true)
    List<Teacher> findTeachersByCareerName(String careerName);
}

