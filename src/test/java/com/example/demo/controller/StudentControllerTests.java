package com.example.demo.controller;

import com.example.demo.model.dto.StudentDTO;
import com.example.demo.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTests {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO();

        doReturn(null).when(studentService).createStudent(any(StudentDTO.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
        verify(studentService, times(1)).createStudent(any(StudentDTO.class));
    }


    @Test
    public void testGetAllStudents() throws Exception {
        when(studentService.getAllStudents()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/student"))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getAllStudents();
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentDTO studentDTO = new StudentDTO();
        when(studentService.getStudentByID(anyLong())).thenReturn(studentDTO);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getStudentByID(anyLong());
    }

    @Test
    public void testGetMaxAge() throws Exception {
        when(studentService.getMaxAge()).thenReturn(30);
        mockMvc.perform(MockMvcRequestBuilders.get("/student/maxAge"))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getMaxAge();
    }

    @Test
    public void testDeleteStudentWithAgeLessThan20() throws Exception {
        doNothing().when(studentService).deleteByName(anyString());
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/John"))
                .andExpect(status().isOk());
        verify(studentService, times(1)).deleteByName(anyString());
    }

    @Test
    public void testDeleteStudentWithAgeLessThan30() throws Exception {
        doNothing().when(studentService).deleteStudentWithAgeLessThan30();
        mockMvc.perform(MockMvcRequestBuilders.delete("/student/DeleteStudents"))
                .andExpect(status().isAccepted());
        verify(studentService, times(1)).deleteStudentWithAgeLessThan30();
    }

    @Test
    public void testGetStudentByName() throws Exception {
        when(studentService.getStudentsByName(anyString())).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/student/x/John"))
                .andExpect(status().isOk());
        verify(studentService, times(1)).getStudentsByName(anyString());
    }

}
