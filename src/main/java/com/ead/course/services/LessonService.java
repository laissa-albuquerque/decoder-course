package com.ead.course.services;

import com.ead.course.models.LessonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {

    LessonModel saveLesson(LessonModel lesson);
    void deleteLesson(LessonModel lesson);
    Optional<LessonModel> findById(UUID id);
    List<LessonModel> findAll();
}
