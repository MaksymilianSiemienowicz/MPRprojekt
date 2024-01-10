package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.enums.Departament;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Test
    public void testFindByName(){

        Course course = new Course();

        course.setName("Test");
        courseRepository.save(course);

        assertEquals("Test", courseRepository.findByName("Test").stream().findFirst().get().getName());


    }

    @Test
    public void testGetDepartamentFroItCourse(){
        Course course = new Course();

        course.setDepartament(Departament.WROCLAW);
        course.setName("IT");

        courseRepository.save(course);

        assertEquals("WROCLAW", courseRepository.getDepartamentFroItCourse().stream().findFirst().orElse(null));
    }

    @Test
    public void testDeleteAllCoursesFromWroclaw(){
        Course course = new Course();

        course.setDepartament(Departament.WROCLAW);

        courseRepository.save(course);

        courseRepository.deleteAllCoursesFromWroclaw();

        assertEquals(null, courseRepository.findAll().stream().findFirst().orElse(null));
    }


    @Test
    public void findStudentsByCourseCity(){

        Course course = new Course();
        course.setDepartament(Departament.WROCLAW);

        Student student = new Student();
        student.setFirstName("Maks");

        student.setCourse(course);

        course.setStudents(List.of(student));

        studentRepository.save(student);
        courseRepository.save(course);

        assertEquals(courseRepository.findStudentsByCourseCity(Departament.WROCLAW).stream().findFirst().get(), student);

    }
}
