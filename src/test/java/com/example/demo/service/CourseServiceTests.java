package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.mapper.course.CourseCreateMapper;
import com.example.demo.model.mapper.course.CourseReadMapper;
import com.example.demo.model.mapper.course.CourseUpdateMapper;
import com.example.demo.repository.CourseRepository;
import lombok.extern.java.Log;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseSerive courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCourseTest() {
        CourseDTO courseDTO = new CourseDTO();
        Course course = new Course();

        Mockito.when(courseRepository.save(any())).thenReturn(course);

        Course result = courseService.createCourse(courseDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(course, result);
    }

    @Test
    void getCourseByIdTest() {
        Long courseId = 1L;
        Course mockCourse = new Course();
        mockCourse.setId(courseId);

        Mockito.when(courseRepository.findById(anyLong())).thenReturn(Optional.of(mockCourse));

        CourseDTO result = courseService.getCourseById(courseId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(courseId, result.getId());
    }

    @Test
    void deleteAllCoursesFromWroclawTest() {
        Mockito.doNothing().when(courseRepository).deleteAllCoursesFromWroclaw();

        courseService.deleteAllCoursesFromWroclaw();

        Mockito.verify(courseRepository, Mockito.times(1)).deleteAllCoursesFromWroclaw();
    }
    @Test
    void getAllCoursesTest() {
        List<Course> mockCourses = new ArrayList<>();
        mockCourses.add(new Course());
        mockCourses.add(new Course());


        Mockito.when(courseRepository.findAll()).thenReturn(mockCourses);

        List<CourseDTO> result = courseService.getAllCourses();

        Assertions.assertEquals(mockCourses.size(), result.size());

    }
}
