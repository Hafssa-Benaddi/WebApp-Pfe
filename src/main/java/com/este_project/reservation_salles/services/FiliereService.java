package com.este_project.reservation_salles.services;

import com.este_project.reservation_salles.models.Filiere;
import com.este_project.reservation_salles.repositories.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiliereService {

  @Autowired
  private FiliereRepository filiereRepository;

  public List<Filiere> findAll() {
    return filiereRepository.findAll();
  }

  public Optional<Filiere> findById(Integer id) {
    return filiereRepository.findById(id);
  }
}
