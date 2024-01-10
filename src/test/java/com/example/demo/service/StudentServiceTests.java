package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class StudentServiceTests {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudentTest() {
        StudentDTO studentDTO = new StudentDTO();
        Student student = new Student();

        when(studentRepository.save(any())).thenReturn(student);

        Student result = studentService.createStudent(studentDTO);

        Assertions.assertNotNull(result);
        assertEquals(student, result);
    }

    @Test
    void getStudentByIdTest() {
        Long studentId = 1L;
        Student mockStudent = new Student();
        mockStudent.setId(studentId);

        when(studentRepository.findById(any())).thenReturn(Optional.of(mockStudent));

        StudentDTO result = studentService.getStudentByID(studentId);

        Assertions.assertNotNull(result);
        assertEquals(studentId, result.getId());
    }

    @Test
    void deleteByNameTest() {
        String studentName = "John";
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student());
        mockStudents.add(new Student());

        when(studentRepository.findByFirstName(anyString())).thenReturn(mockStudents);
        Mockito.doNothing().when(studentRepository).deleteAll(mockStudents);

        studentService.deleteByName(studentName);

        Mockito.verify(studentRepository, Mockito.times(1)).deleteAll(mockStudents);
    }

    @Test
    void getAllStudentsTest() {
        List<Student> mockStudents = new ArrayList<>();
        mockStudents.add(new Student());
        mockStudents.add(new Student());


        when(studentRepository.findAll()).thenReturn(mockStudents);

        List<StudentDTO> result = studentService.getAllStudents();

        assertEquals(mockStudents.size(), result.size());

    }

    @Test
    public void testGetStudentsByName() {
        String name = "John";
        Student john = new Student();
        john.setFirstName("John");
        Student jane = new Student();
        jane.setFirstName("Jane");

        when(studentRepository.findByFirstName(name))
                .thenReturn(Arrays.asList(john, jane));

        List<StudentDTO> result = studentService.getStudentsByName(name);

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFirstName());
        assertEquals("Jane", result.get(1).getFirstName());

    }
}
