package com.este_project.reservation_salles.services;

import com.este_project.reservation_salles.models.User;
import com.este_project.reservation_salles.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> findAllProfessors() {
    return userRepository.findByRole(User.Role.professor);
  }

  public Optional<User> findById(Integer id) {
    return userRepository.findById(id);
  }
}
