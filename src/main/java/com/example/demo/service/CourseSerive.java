package com.example.demo.service;

import com.example.demo.exception.InvalidFieldValueException;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.model.enums.Departament;
import com.example.demo.model.mapper.course.CourseCreateMapper;
import com.example.demo.model.mapper.course.CourseReadMapper;
import com.example.demo.model.mapper.student.StudentCreateMapper;
import com.example.demo.model.mapper.student.StudentReadMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseSerive {

    private final CourseRepository courseRepository;


    public Course createCourse(CourseDTO courseDTO) {
        Course course = CourseCreateMapper.toEntity(courseDTO);
        return courseRepository.save(course);
    }

    public CourseDTO getCourseById(Long id) {
        if(id <= 0){
            throw new InvalidFieldValueException("Value cannot 0 or less");
        }
        Course course = courseRepository.findById(id).orElse(null);
        return CourseReadMapper.toDTO(course);
    }

    public String getDepartamentFroItCourse(){
        return courseRepository.getDepartamentFroItCourse().stream().findFirst().orElse(null);
    }

    public List<StudentDTO> findStudentsByCourseCity(Departament departament){
        List<StudentDTO> studentDTOS = new ArrayList<>();

        courseRepository.findStudentsByCourseCity(departament).
                stream().map(StudentReadMapper::toDTO).
                forEach(s -> studentDTOS.add(s));

        return studentDTOS;
    }

    public void deleteAllCoursesFromWroclaw() {
        courseRepository.deleteAllCoursesFromWroclaw();
    }

    public List<CourseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(CourseReadMapper::toDTO)
                .collect(Collectors.toList());
    }



}
