package com.example.student.services;

import com.example.shared.service.IGenericService;
import com.example.student.Dto.StudentDto;
import com.example.student.model.Student;
import com.example.shared.service.IGenericService;

import java.util.List;
import java.util.Map;

public interface IStudentService extends IGenericService<Student> {

    Student add(Student Student);

    Student update(long id, Map<Object, Object> fields);

    boolean delete(long id);

    StudentDto getStudent(long id);
     List<StudentDto> getAll();
}
