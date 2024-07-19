package com.example.student.services;


import com.example.student.Dto.StudentDto;
import com.example.student.Dto.CourseDto;
import com.example.student.FeignClient.CourseClient;
import com.example.student.model.Student;
import com.example.student.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IStudentServiceImp implements IStudentService {

    private final StudentRepository StudentClient;
    private final CourseClient courseFeignClient;
    private final RestTemplate restTemplate;

    @Override
    public Student add(Student Student) {
        return StudentClient.save(Student);
    }

    @Override
    public Student update(long id, Map<Object, Object> fields) {
        Student stock = StudentClient.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Student not found: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Student.class, (String) key);
            field.setAccessible(true);


        });
        return StudentClient.save(stock);
    }

    @Override
    public boolean delete(long id) {
        StudentClient.deleteById(id);
        return StudentClient.existsById(id);    }


   @Override
   public StudentDto getStudent(long id) {
       Student Student = StudentClient.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
       String courseId = Student.getCourseId();
       System.out.println("Session ID: " + courseId);
       CourseDto courseDto = courseFeignClient.getCourseById(courseId);
       return StudentDto.builder()
               .firstname(Student.getFirstName())
               .lastname(Student.getLastName())
               .courseDto(courseDto)
               .build();
   }


   /* @Override
    public StudentDto getStudent(long id) {
        Student Student = StudentClient.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        String courseId = Student.getCourseIdId();
        System.out.println("Session ID: " + sessionId);

        // Utilisation de RestTemplate pour appeler le service cours
        CourseDto courseDto = restTemplate.getForObject("http://localhost:8082/course/{courseId}",
                CourseDto.class, courseId);

        return StudentDto.builder()
                 .firstname(Student.getFirstName())
               .lastname(Student.getLastName())
               .courseDto(courseDto)
               .build();
    }*/

    @Override
    public List<StudentDto> getAll() {
        List<Student> Students = StudentClient.findAll();
        return Students.stream()
                .map(Student -> {
                    CourseDto courseDto = courseFeignClient.getCourseById(Student.getCourseId());
                    return StudentDto.builder()
                            .firstname(Student.getFirstName())
                            .lastname(Student.getLastName())
                            .courseDto(courseDto)
                            .build();
                })
                .collect(Collectors.toList());
    }


}
