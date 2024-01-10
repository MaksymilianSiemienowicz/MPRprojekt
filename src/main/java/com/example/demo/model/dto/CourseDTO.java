package com.example.demo.model.dto;


import com.example.demo.model.Student;
import com.example.demo.model.enums.Departament;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDTO {

    private Long id;

    @NotBlank(message = "Course name is mandatory")
    private String name;

    @NotNull(message = "Department is mandatory")
    private Departament departament;

    @NotBlank(message = "Lecture last name is mandatory")
    private String lectureLastName;

    private List<Student> students;

}

