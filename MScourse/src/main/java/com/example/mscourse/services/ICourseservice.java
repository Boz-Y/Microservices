package com.example.mscourse.services;



import com.example.mscourse.model.Course;

import java.util.Map;
import java.util.Set;

public interface ICourseservice {


    Course add(Course course);

    Course update(String id, Map<Object,Object> fields);

    boolean delete(String id);


    Course getCourse(String id);

    Set<Course> getAllCourses();
}
