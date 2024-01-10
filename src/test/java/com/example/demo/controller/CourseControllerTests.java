package com.example.demo.controller;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.model.enums.Departament;
import com.example.demo.service.CourseSerive;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Collections;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = CourseController.class)
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseSerive courseService;

    @Test
    public void createCourse_ValidCourseDetails_Created() throws Exception {
        CourseDTO courseDTO = new CourseDTO();

        ObjectMapper objectMapper = new ObjectMapper();
        String courseJson = objectMapper.writeValueAsString(courseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void findCourseById_ExistingId_ReturnsCourseDetails() throws Exception {
        Long courseId = 1L;
        CourseDTO mockCourseDTO = new CourseDTO();

        when(courseService.getCourseById(courseId)).thenReturn(mockCourseDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/{id}", courseId))
                .andExpect(status().isOk());

    }

    @Test
    public void getDepartmentForITCourse_ValidRequest_ReturnsDepartmentName() throws Exception {
        String departmentName = "Wroclaw";
        when(courseService.getDepartamentFroItCourse()).thenReturn(departmentName);

        mockMvc.perform(MockMvcRequestBuilders.get("/course/DepartamentForIT"))
                .andExpect(status().isOk())
                .andExpect(content().string(departmentName));
    }

    @Test
    public void findStudentsByCourseDepartment_ValidDepartment_ReturnsStudentList() throws Exception {
        Departament department = Departament.WROCLAW;
        StudentDTO mockStudentDTO = new StudentDTO();

        when(courseService.findStudentsByCourseCity(department)).thenReturn(Collections.singletonList(mockStudentDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/course/x/{department}", department))
                .andExpect(status().isOk());

    }

    @Test
    public void deleteAllCoursesFromWroclaw_ValidRequest_SuccessfullyDeletes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/course/deleteWroclaw"))
                .andExpect(status().isOk());
    }

    @Test
    public void findAllCourses_ValidRequest_ReturnsCourseList() throws Exception {
        CourseDTO mockCourseDTO = new CourseDTO();

        when(courseService.getAllCourses()).thenReturn(Collections.singletonList(mockCourseDTO));

        mockMvc.perform(MockMvcRequestBuilders.get("/course"))
                .andExpect(status().isOk());

    }
}
