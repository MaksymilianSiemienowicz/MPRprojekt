package com.example.demo.repository;

import com.example.demo.model.Course;
import com.example.demo.model.Student;
import com.example.demo.model.enums.Departament;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByName(String name);


    @Query("select c.departament from Course c WHERE c.name='IT'")
    Optional<String> getDepartamentFroItCourse();


    @Transactional
    @Modifying
    @Query("delete from Course c where c.departament = 'WROCLAW'")
    void deleteAllCoursesFromWroclaw();

    @Query("SELECT s FROM Student s JOIN s.course c WHERE c.departament = ?1")
    List<Student> findStudentsByCourseCity(Departament departament);

}
