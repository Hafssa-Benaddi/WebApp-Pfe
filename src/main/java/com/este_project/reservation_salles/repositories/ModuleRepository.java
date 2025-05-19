package com.este_project.reservation_salles.repositories;

import com.este_project.reservation_salles.models.Module;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
