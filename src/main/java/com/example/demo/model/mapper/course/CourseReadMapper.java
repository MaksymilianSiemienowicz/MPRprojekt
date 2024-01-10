package com.example.demo.model.mapper.course;

import com.example.demo.model.Course;
import com.example.demo.model.dto.CourseDTO;
import org.springframework.beans.BeanUtils;

public class CourseReadMapper {
    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        BeanUtils.copyProperties(course, dto);
        return dto;
    }
}
