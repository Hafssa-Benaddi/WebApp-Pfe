package com.este_project.reservation_salles.controllers;

import com.este_project.reservation_salles.models.Module;

import com.este_project.reservation_salles.services.FiliereService;
import com.este_project.reservation_salles.services.ModuleService;
import com.este_project.reservation_salles.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modules")
public class ModuleController {

  @Autowired
  private FiliereService filiereService;

  @Autowired
  private ModuleService moduleService;

  @Autowired
  private UserService userService;

  @GetMapping
  public String getModules(Model model) {
    model.addAttribute("filieres", filiereService.findAll());
    model.addAttribute("professors", userService.findAllProfessors());
    model.addAttribute("filieres", filiereService.findAll());
    model.addAttribute("newModule", new Module());
    return "modules";
  }

  @PostMapping("/add")
  public String addModule(@ModelAttribute("newModule") Module module,
      @RequestParam("filiereId") Integer filiereId,
      @RequestParam(value = "professorId", required = false) Integer professorId) {

    filiereService.findById(filiereId).ifPresent(module::setFiliere);

    if (professorId != null) {
      userService.findById(professorId).ifPresent(module::setProfessor);
    } else {
      module.setProfessor(null);
    }

    moduleService.save(module);

    return "redirect:/modules";
  }
}
