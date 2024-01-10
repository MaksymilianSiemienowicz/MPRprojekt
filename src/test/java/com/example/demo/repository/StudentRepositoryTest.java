package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.enums.Departament;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testFindStudentByFirstName() {

        String firstName = "Maks";

        // Tworzymy studenta i zapisujemy go w repozytorium
        Student student = new Student();
        student.setFirstName(firstName);
        studentRepository.save(student);



        assertEquals(student,studentRepository.findByFirstName(firstName).stream().findFirst().orElse(null));
    }

    @Test
    public void testFindMaxAge() {

        Integer age = 15;
        Integer age2 = 19;

        Student student = new Student();
        Student student2 = new Student();

        student.setAge(age);
        student2.setAge(age2);

        studentRepository.save(student);
        studentRepository.save(student2);

        Integer maxAge = studentRepository.findMaxAge().orElse(null);

        assertEquals(maxAge, age2);

    }

    @Test
    public void deleteStudentWithAgeLessThan30(){

        Student student = new Student();
        student.setAge(20);

        studentRepository.save(student);
        studentRepository.deleteStudentWithAgeLessThan30();

        assertEquals(null, studentRepository.findAll().stream().findFirst().orElse(null));

    }



}
