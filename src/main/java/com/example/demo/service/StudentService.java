package com.example.demo.service;

import com.example.demo.exception.InvalidFieldValueException;
import com.example.demo.exception.NotAllowedOperation;
import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.model.mapper.student.StudentCreateMapper;
import com.example.demo.model.mapper.student.StudentReadMapper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    private final CourseRepository courseRepository;

    public Student createStudent(StudentDTO studentDTO) {

        Student student = StudentCreateMapper.toEntity(studentDTO);

        return studentRepository.save(student);
    }


    public StudentDTO getStudentByID(Long id) {
        if (id <= 0){
                throw new InvalidFieldValueException("Value cannot 0 or less");
        }
        Student student = studentRepository.findById(id).orElse(null);
        return StudentReadMapper.toDTO(student);
    }

    public void deleteByName(String name) {
        List<Student> students = studentRepository.findByFirstName(name);
        studentRepository.deleteAll(students);
    }

    public List<StudentDTO> getStudentsByName(String name){
        List<StudentDTO> studentDTOS = new ArrayList<>();
        studentRepository.findByFirstName(name).stream().forEach(s -> {
            studentDTOS.add(StudentReadMapper.toDTO(s));
        });

        return studentDTOS;
    }

    public Integer getMaxAge(){
        return studentRepository.findMaxAge().stream().findFirst().orElse(null);
    }

    public void deleteStudentWithAgeLessThan30(){
        studentRepository.deleteStudentWithAgeLessThan30();
    }

    public List<StudentDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(StudentReadMapper::toDTO)
                .collect(Collectors.toList());
    }
}
