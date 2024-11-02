package com.ead.course.controllers.mappers;

import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.models.dtos.ModuleDto;
import org.springframework.stereotype.Component;

@Component
public class ModuleMapper {

    public ModuleModel toModel(ModuleDto module, CourseModel course) {
        return ModuleModel.builder()
                .title(module.title())
                .description(module.description())
                .course(course)
                .build();
    }
}
