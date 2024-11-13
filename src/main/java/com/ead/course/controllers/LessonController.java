package com.ead.course.controllers;

import com.ead.course.controllers.mappers.LessonMapper;
import com.ead.course.models.LessonModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.models.dtos.LessonDto;
import com.ead.course.services.LessonService;
import com.ead.course.services.ModuleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RestController
@RequestMapping(value = "lessons")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class LessonController {

    private final LessonService lessonService;
    private final ModuleService moduleService;
    private final LessonMapper mapper;

    @PostMapping
    ResponseEntity<Object> saveLesson(@RequestBody @Valid LessonDto lesson, @RequestParam String moduleName) {
        Optional<ModuleModel> moduleSelect;
        if (!moduleName.isEmpty() || moduleName != null) {
            moduleSelect = moduleService.findByTitle(moduleName);
            if (moduleSelect.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.saveLesson(mapper.toLesson(lesson, moduleSelect.get())));
            } else ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found in the database");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It is necessary to associate a lesson with a module!");
    }

    @GetMapping("/{lessonId}")
    ResponseEntity<Object> getLessonById(@PathVariable(value = "lessonId") UUID lessonId) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findById(lessonId));
    }

    @GetMapping
    ResponseEntity<List<LessonModel>> getAllLessons() {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAll());
    }

    @DeleteMapping("/{lessonId}")
    ResponseEntity<Object> deleteLesson(@PathVariable(value = "lessonId") UUID lessonId) {
        Optional<LessonModel> lessonSelected = lessonService.findById(lessonId);
        if (lessonSelected.isPresent()) {
            lessonService.deleteLesson(lessonSelected.get());
            return ResponseEntity.status(HttpStatus.OK).body("Lesson deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found in the database");
    }

    @PutMapping("/{lessonId}")
    ResponseEntity<Object> updateLesson(@PathVariable(value = "lessonId") UUID lessonId, @RequestBody @Valid LessonDto lesson) {
        Optional<LessonModel> lessonSelected = lessonService.findById(lessonId);
        if (!lessonSelected.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson not found in the database");
        }
        LessonModel lessonUpdated = lessonSelected.get();
        lessonUpdated.setTitle(lesson.title());
        lessonUpdated.setDescription(lesson.description());
        lessonUpdated.setVideoUrl(lesson.videoUrl());
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.saveLesson(lessonUpdated));
    }
}
