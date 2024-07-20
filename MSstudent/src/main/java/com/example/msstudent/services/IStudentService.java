package com.example.msstudent.services;

import com.example.msstudent.Dto.StudentDto;
import com.example.msstudent.model.Student;
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
