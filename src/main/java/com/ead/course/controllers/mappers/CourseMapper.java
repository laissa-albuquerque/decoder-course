package com.ead.course.controllers.mappers;

import com.ead.course.models.CourseModel;
import com.ead.course.models.dtos.CourseDto;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseModel toEntity(CourseDto course) {
        return CourseModel.builder()
                .name(course.name())
                .description(course.description())
                .imageUrl(course.imageUrl())
                .courseStatus(course.courseStatus())
                .userInstructor(course.userInstructor())
                .courseLevel(course.courseLevel())
                .build();
    }
}
