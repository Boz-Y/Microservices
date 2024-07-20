package com.example.msstudent.services;


import com.example.msstudent.model.Course;
import com.example.msstudent.repository.CourseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final CourseRepository CourseRepository;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "course-changes", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Gérer les messages (ex : ADD: {"id":"1","name":"Course1"}, UPDATE: {"id":"1","name":"Course1"}, DELETE: 1)
        try {
            String[] messageParts = message.split(": ", 2);
            String action = messageParts[0];
            String payload = messageParts[1];

            switch (action) {
                case "ADD":
                case "CREATE":  // Ajoutez ce cas pour accepter l'action "CREATE"
                case "UPDATE":
                    Course course = objectMapper.readValue(payload, Course.class);
                    CourseRepository.save(course);
                    System.out.println("Course saved: " + course);
                    break;
                case "DELETE":
                    CourseRepository.deleteById(payload);
                    System.out.println("Course deleted: " + payload);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown action: " + action);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}