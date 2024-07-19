package com.example.student.FeignClient;

import com.example.student.Dto.CourseDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "course", url = "http://localhost:8082/course" )
public interface CourseClient {

    @GetMapping("/{courseId}")
    @CircuitBreaker(name = "CourseClient", fallbackMethod = "getCourseByIdFallback")
    CourseDto getCourseById(@PathVariable("courseId") String sessionId);

    default CourseDto getCourseByIdFallback(String courseId, Throwable t) {
        return new CourseDto("le cours pour le moment est indisonible , réesayer plus tard");
    }

}

