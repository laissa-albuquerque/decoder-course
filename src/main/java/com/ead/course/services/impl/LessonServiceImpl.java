package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.services.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;

    @Override
    public LessonModel saveLesson(LessonModel lesson) {
        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(LessonModel lesson) {
        lessonRepository.delete(lesson);
    }

    @Override
    public Optional<LessonModel> findById(UUID id) {
        return lessonRepository.findById(id);
    }

    @Override
    public List<LessonModel> findAll() {
        return lessonRepository.findAll();
    }
}
