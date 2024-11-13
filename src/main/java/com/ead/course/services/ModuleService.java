package com.ead.course.services;

import com.ead.course.models.ModuleModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    ModuleModel saveModel(ModuleModel moduleModel);
    void delete(ModuleModel module);
    Optional<ModuleModel> findById(UUID idModule);
    List<ModuleModel> findAll();
    Optional<ModuleModel> findByTitle(String name);
}
