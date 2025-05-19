package com.este_project.reservation_salles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminEspaceController {

  @GetMapping("/admin")
  public String espaceAdmin() {
    return "espace_admin";
  }
}
