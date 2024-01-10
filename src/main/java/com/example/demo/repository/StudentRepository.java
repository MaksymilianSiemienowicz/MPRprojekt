package com.example.demo.repository;

import com.example.demo.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstName(String firstName); //tested

    @Query("select max(s.age) from Student s")
    Optional<Integer> findMaxAge();

    @Transactional
    @Modifying
    @Query("delete from Student s where s.age < 30")
    void deleteStudentWithAgeLessThan30();

}
