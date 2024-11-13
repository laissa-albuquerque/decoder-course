package com.ead.course.models.dtos;

import com.ead.course.models.ModuleModel;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record LessonDto(
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        String videoUrl,
        ModuleModel module) {
}
