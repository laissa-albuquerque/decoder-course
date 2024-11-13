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
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    @Override
    public ModuleModel saveModel(ModuleModel moduleModel) {
        return moduleRepository.save(moduleModel);
    }

    @Transactional
    @Override
    public void delete(ModuleModel module) {
        List<LessonModel> lessons = lessonRepository.findAllLessonsIntoModule(module.getIdModule());
        if(!lessons.isEmpty()) {
            lessonRepository.deleteAll(lessons);
        }
        moduleRepository.delete(module);
    }

    @Override
    public Optional<ModuleModel> findById(UUID idModule) {
        return moduleRepository.findById(idModule);
    }

    @Override
    public List<ModuleModel> findAll() {
        return moduleRepository.findAll();
    }

    @Override
    public Optional<ModuleModel> findByTitle(String name) {
        return moduleRepository.findByTitleIgnoreCase(name);
    }
}
