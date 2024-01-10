package com.example.demo.model.mapper.student;


import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentDTO;
import org.springframework.beans.BeanUtils;

public class StudentCreateMapper {
    public static Student toEntity(StudentDTO dto) {
        Student student = new Student();
        BeanUtils.copyProperties(dto, student);
        return student;
    }
}

