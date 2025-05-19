package com.este_project.reservation_salles.repositories;

import com.este_project.reservation_salles.models.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FiliereRepository extends JpaRepository<Filiere, Integer> {

  @Query("SELECT f FROM Filiere f LEFT JOIN FETCH f.responsable")
  List<Filiere> findAllWithResponsable();
}
