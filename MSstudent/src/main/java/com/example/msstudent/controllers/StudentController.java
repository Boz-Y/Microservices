package com.example.msstudent.controllers;


import com.example.msstudent.Dto.StudentDto;
import com.example.msstudent.model.Student;
import com.example.msstudent.services.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final IStudentService iStudentService;

    @PostMapping
    public Student add(@RequestBody Student Student) {
        return iStudentService.add(Student);
    }

    @PatchMapping("/{id}")
    public Student patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable long id){
        return iStudentService.update(id,fields);
    }

    @DeleteMapping("/{id}")
    public boolean delete( @PathVariable long id){
        return iStudentService.delete(id);
    }


    @GetMapping("/all")
    public List<StudentDto> getAll(){return (List<StudentDto>) iStudentService.getAll();}


    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        return iStudentService.getStudent(id);
    }
}
