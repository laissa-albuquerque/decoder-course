package com.ead.course.services;

import com.ead.course.models.CourseModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourseService {

    void delete(CourseModel course);
    CourseModel save(CourseModel course);
    Optional<CourseModel> findById(UUID id);
    List<CourseModel> findAll();
    Optional<CourseModel> findByName(String name);
}
