package com.example.demo.model.mapper.course;

import com.example.demo.model.Course;
import com.example.demo.model.dto.CourseDTO;
import org.springframework.beans.BeanUtils;

public class CourseUpdateMapper {
    public static Course toEntity(CourseDTO dto, Course existingCourse) {
        BeanUtils.copyProperties(dto, existingCourse);
        return existingCourse;
    }
}
