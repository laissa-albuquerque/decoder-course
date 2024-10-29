package com.ead.course.controllers;

import com.ead.course.controllers.mappers.CourseMapper;
import com.ead.course.models.CourseModel;
import com.ead.course.models.dtos.CourseDto;
import com.ead.course.services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @PostMapping
    public ResponseEntity<CourseModel> saveCourse(@RequestBody @Valid CourseDto course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseMapper.toEntity(course)));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId) {
        Optional<CourseModel> course = courseService.findById(courseId);
        if(course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");

        courseService.delete(course.get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted successfully");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId, @RequestBody @Valid CourseDto courseDto) {
        Optional<CourseModel> course = courseService.findById(courseId);
        if(course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");

        var courseUpdated = course.get();
        courseUpdated.setName(courseDto.name());
        courseUpdated.setDescription(courseDto.description());
        courseUpdated.setCourseStatus(courseDto.courseStatus());
        courseUpdated.setImageUrl(courseDto.imageUrl());
        courseUpdated.setCourseLevel(courseDto.courseLevel());
        courseUpdated.setUserInstructor(courseDto.userInstructor());
        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseUpdated));
    }

    @GetMapping
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> getCourseById(@PathVariable(value = "courseId") UUID courseId) {
        Optional<CourseModel> course = courseService.findById(courseId);
        if(course.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found");

        return ResponseEntity.status(HttpStatus.OK).body(course.get());
    }
}
