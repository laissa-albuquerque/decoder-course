package com.ead.course.models.dtos;

import com.ead.course.models.CourseModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record ModuleDto(
        @NotNull
        String title,
        @NotNull
        String description,
        CourseModel course) {}
