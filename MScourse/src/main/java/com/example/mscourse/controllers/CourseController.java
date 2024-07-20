package com.example.mscourse.controllers;


import com.example.mscourse.model.Course;
import com.example.mscourse.services.ICourseservice;
import com.example.mscourse.services.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Course")
@RequiredArgsConstructor
public class CourseController {
    private final ICourseservice iCourseservice;

   /* @PostMapping
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
    }*/

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public Course add(@RequestBody Course Course) {
        Course createdCourse = iCourseservice.add(Course);
        try {
            // Convertir la Course en JSON
            String CourseJson = new ObjectMapper().writeValueAsString(createdCourse);
            // Envoyer le message complet
            kafkaProducer.sendMessage("CREATE: " + CourseJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
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
    }
  
}
