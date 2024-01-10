package com.example.demo.model.mapper.student;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentDTO;
import org.springframework.beans.BeanUtils;

public class StudentUpdateMapper {
    public static Student toEntity(StudentDTO dto, Student existingStudent, Course course) {
        BeanUtils.copyProperties(dto, existingStudent);
        existingStudent.setCourse(course);
        return existingStudent;
    }
}
