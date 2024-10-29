package com.ead.course.models.dtos;

import com.ead.course.enums.CourseLevel;
import com.ead.course.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        String imageUrl,
        @NotNull
        CourseStatus courseStatus,
        @NotNull
        UUID userInstructor,
        @NotNull
        CourseLevel courseLevel) {}

