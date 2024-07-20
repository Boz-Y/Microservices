package com.example.mscourse.services;

import com.example.mscourse.repository.CourseRepository;
import com.example.mscourse.model.Course;
import com.example.mscourse.services.ICourseservice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class ICourseServiceImp implements ICourseservice {

    private final CourseRepository CourseRepository;

    @Override
    public Course add(Course Course) {
        return CourseRepository.save(Course);
    }

    @Override
    public Course update(String id, Map<Object, Object> fields) {
        Course Course = CourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found: " + id));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Course.class, (String) key);
            field.setAccessible(true);


        });
        return CourseRepository.save(Course);
    }

    @Override
    public boolean delete(String id) {
        CourseRepository.deleteById(id);
        return CourseRepository.existsById(id);    }

    @Override
    public Course getCourse(String id) {
        return CourseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));    }

    @Override
    public Set<Course> getAllCourses() {
        return (Set<Course>) CourseRepository.findAll();
    }
}
