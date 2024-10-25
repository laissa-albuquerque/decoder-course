package com.ead.course.services.impl;

import com.ead.course.models.CourseModel;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.repositories.CourseRepository;
import com.ead.course.repositories.LessonRepository;
import com.ead.course.repositories.ModuleRepository;
import com.ead.course.services.CourseService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(CourseModel course) {
        List<ModuleModel> modules = moduleRepository.findAllModulesIntoCourse(course.getCourseId());
        if(!modules.isEmpty()) {
            for(ModuleModel module : modules) {
                List<LessonModel> lessons = lessonRepository.findAllLessonsIntoModule(module.getIdModule());
                if(!lessons.isEmpty()) {
                    lessonRepository.deleteAll(lessons);
                }
            }
            moduleRepository.deleteAll(modules);
        }
        courseRepository.delete(course);
    }
}
