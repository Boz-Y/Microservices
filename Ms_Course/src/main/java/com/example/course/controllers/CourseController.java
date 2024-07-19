package com.example.course.controllers;

import com.example.course.model.Course;
import com.example.course.services.ICourseservice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Course")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseservice iCourseservice;

    @PostMapping
    public Course add(@RequestBody Course Course) {
        return iCourseservice.add(Course);
    }

    @PatchMapping("{id}")
    public Course patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return iCourseservice.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return iCourseservice.delete(id);
    }


    @GetMapping
    public List<Course> getAll(){return (List<Course>) iCourseservice.getAllCourses();}


    @GetMapping("/{id}")
    public Course getCourse(@PathVariable String id){
        return iCourseservice.getCourse(id);
    }
  /* @PostMapping
   public Course add(@RequestBody Course Course) {
       Course createdCourse = iCourseservice.add(Course);
       kafkaProducer.sendMessage("CREATE: " + createdCourse.getId());
       return createdCourse;
   }

    @PatchMapping("{id}")
    public Course patchUpdate(@RequestBody Map<Object, Object> fields, @PathVariable String id) {
        Course updatedCourse = iCourseservice.update(id, fields);
        kafkaProducer.sendMessage("UPDATE: " + id);
        return updatedCourse;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable String id) {
        boolean isDeleted = iCourseservice.delete(id);
        if (isDeleted) {
            kafkaProducer.sendMessage("DELETE: " + id);
        }
        return isDeleted;
    }*/
}
