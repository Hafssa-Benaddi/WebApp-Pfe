package com.este_project.reservation_salles.services;

import com.este_project.reservation_salles.models.Module;
import com.este_project.reservation_salles.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

  @Autowired
  private ModuleRepository moduleRepository;

  public List<Module> findAll() {
    return moduleRepository.findAll();
  }

  public Module save(Module module) {
    return moduleRepository.save(module);
  }
}
