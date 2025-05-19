package com.este_project.reservation_salles.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.este_project.reservation_salles.models.Salle;

public interface SalleRepository extends JpaRepository<Salle, Integer> {

}
