package com.ead.course.controllers.mappers;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.models.dtos.LessonDto;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public LessonModel toLesson(LessonDto lesson, ModuleModel module) {
        return LessonModel.builder()
                .title(lesson.title())
                .description(lesson.description())
                .videoUrl(lesson.videoUrl())
                .module(module)
                .build();
    }
}
