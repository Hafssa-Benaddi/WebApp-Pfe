package com.este_project.reservation_salles.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.este_project.reservation_salles.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
  List<User> findByRole(User.Role role);

  Optional<User> findByEmailAcademic(String emailAcademic);
}
