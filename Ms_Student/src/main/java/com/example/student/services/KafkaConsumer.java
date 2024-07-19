package com.example.student.services;

import com.example.student.model.Course;
import com.example.student.repository.CourseRepository;
import com.example.student.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "course-changes", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Gérer les messages (ex : ADD: {"id":"1","name":"course1"}, UPDATE: {"id":"1","name":"course1"}, DELETE: 1)
        try {
            String[] messageParts = message.split(": ", 2);
            String action = messageParts[0];
            String payload = messageParts[1];

            switch (action) {
                case "ADD":
                case "UPDATE":
                    Course course = objectMapper.readValue(payload, Course.class);
                    courseRepository.save(course);
                    break;
                case "DELETE":
                    courseRepository.deleteById(payload);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}