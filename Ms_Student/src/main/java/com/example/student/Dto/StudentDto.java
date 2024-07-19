package com.example.student.Dto;

import lombok.Builder;

@Builder
public record StudentDto(String firstname, String lastname, CourseDto courseDto){

}

