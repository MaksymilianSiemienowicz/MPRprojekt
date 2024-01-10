package com.example.demo.controller;

import com.example.demo.model.dto.CourseDTO;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.model.enums.Departament;
import com.example.demo.service.CourseSerive;
import jakarta.persistence.ManyToMany;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
@CrossOrigin
public class CourseController {

    private final CourseSerive courseSerive;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody @Validated CourseDTO courseDTO){
        courseSerive.createCourse(courseDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CourseDTO findById(@PathVariable Long id){
        return courseSerive.getCourseById(id);
    }

    @GetMapping("/DepartamentForIT")
    @ResponseStatus(HttpStatus.OK)
    public String getDepartamentForITcourse(){
        return courseSerive.getDepartamentFroItCourse();
    }

    @GetMapping("/x/{departament}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> findStudentsByCourseCity(@PathVariable Departament departament){
        return courseSerive.findStudentsByCourseCity(departament);
    }

    @DeleteMapping("/deleteWroclaw")
    @ResponseStatus(HttpStatus.OK)
    public void delteWroclaw(){
        courseSerive.deleteAllCoursesFromWroclaw();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CourseDTO> findAllCourses(){
        return courseSerive.getAllCourses();
    }

}
