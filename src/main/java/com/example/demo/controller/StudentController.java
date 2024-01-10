package com.example.demo.controller;

import com.example.demo.exception.NotAllowedOperation;
import com.example.demo.model.dto.StudentDTO;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {

    private final StudentService studentService;
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody @Validated StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentDTO getStudentById(@PathVariable Long id){
        return studentService.getStudentByID(id);
    }

    @GetMapping("/maxAge")
    @ResponseStatus(HttpStatus.OK)
    public Integer getMaxAge(){
        return studentService.getMaxAge();
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStudentByName(@PathVariable String name){
        studentService.deleteByName(name);
    }


    @DeleteMapping("/DeleteStudents")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteStudentWithAgeLessThan30(){
        studentService.deleteStudentWithAgeLessThan30();
    }

    @GetMapping("/x/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentDTO> getStudentByName(@PathVariable String name){
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/admin")
    public void blockAdminPanel(){
        throw new NotAllowedOperation();
    }

}
