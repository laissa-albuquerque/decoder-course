package com.ead.course.services.impl;

import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.ModuleService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(ModuleModel module) {
        List<LessonModel> lessons = lessonRepository.findAllLessonsIntoModule(module.getIdModule());
        if(!lessons.isEmpty()) {
            lessonRepository.deleteAll(lessons);
        }
        moduleRepository.delete(module);
    }
}
