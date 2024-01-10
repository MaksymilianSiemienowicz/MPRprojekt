package com.example.demo.model.mapper.student;

import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentDTO;
import org.springframework.beans.BeanUtils;

public class StudentReadMapper {
    public static StudentDTO toDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        BeanUtils.copyProperties(student, dto);
        if (student.getCourse() != null) {
            dto.setCourse(student.getCourse());
        }
        return dto;
    }
}
