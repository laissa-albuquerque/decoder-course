package com.ead.course.controllers;

import com.ead.course.controllers.mappers.ModuleMapper;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.models.dtos.ModuleDto;
import com.ead.course.services.CourseService;
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
@RequestMapping(value = "/modules")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class ModuleController {

    private final ModuleService moduleService;
    private final CourseService courseService;
    private final ModuleMapper moduleMapper;

    @PostMapping
    public ResponseEntity<Object> saveModule(@RequestBody @Valid ModuleDto module, @RequestParam String courseName) {
        Optional<CourseModel> courseSelected;
        if (courseName != null && !courseName.isEmpty()) {
            courseSelected = courseService.findByName(courseName);
            if (courseSelected.isPresent()) {
                return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.saveModel(moduleMapper.toModel(module, courseSelected.get())));
            } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found in the database.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("It is necessary to associate a module with a course!");
    }

    @DeleteMapping("/{moduleId}")
    public ResponseEntity<Object> deleteModule(@PathVariable(value = "moduleId") UUID moduleId) {
        Optional<ModuleModel> moduleSelected = moduleService.findById(moduleId);
        if (moduleSelected.isPresent()) {
            moduleService.delete(moduleSelected.get());
            return ResponseEntity.status(HttpStatus.OK).body("Module deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
    }

    @PutMapping("/{moduleId}")
    public ResponseEntity<Object> updateModule(@PathVariable(value = "moduleId") UUID moduleId, @RequestBody @Valid ModuleDto module) {
        Optional<ModuleModel> moduleSelected = moduleService.findById(moduleId);
        if (!moduleSelected.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
        }
        ModuleModel moduleUpdated = moduleSelected.get();
        moduleUpdated.setTitle(module.title());
        moduleUpdated.setDescription(module.description());
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.saveModel(moduleUpdated));
    }

    @GetMapping
    ResponseEntity<List<ModuleModel>> getAllModules() {
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAll());
    }

    @GetMapping("/{moduleId}")
    ResponseEntity<Object> getModuleById(@PathVariable(value = "moduleId") UUID moduleId) {
        Optional<ModuleModel> moduleSelected = moduleService.findById(moduleId);
        if (!moduleSelected.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleSelected);
    }
}
