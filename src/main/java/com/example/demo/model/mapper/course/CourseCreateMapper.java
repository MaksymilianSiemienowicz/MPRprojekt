package com.example.demo.model.mapper.course;

import com.example.demo.model.Course;
import com.example.demo.model.dto.CourseDTO;
import org.springframework.beans.BeanUtils;

public class CourseCreateMapper {
    public static Course toEntity(CourseDTO dto) {
        Course course = new Course();
        BeanUtils.copyProperties(dto, course);
        return course;
    }
}
