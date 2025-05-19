package com.este_project.reservation_salles.controllers;

import com.este_project.reservation_salles.models.Filiere;
import com.este_project.reservation_salles.models.User;
import com.este_project.reservation_salles.repositories.FiliereRepository;
import com.este_project.reservation_salles.repositories.UserRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filieres")
public class FiliereController {

  private final FiliereRepository filiereRepository;
  private final UserRepository userRepository;

  public FiliereController(FiliereRepository filiereRepository, UserRepository userRepository) {
    this.filiereRepository = filiereRepository;
    this.userRepository = userRepository;
  }

  @GetMapping
  public String getAllFilieres(Model model) {
    // Récupérer toutes les filières avec leur responsable
    List<Filiere> filieres = filiereRepository.findAllWithResponsable();

    // Récupérer les professeurs (utilisateurs ayant le rôle 'PROFESSOR')
    List<User> professeurs = userRepository.findByRole(User.Role.professor);

    // Ajouter les niveaux possibles
    List<String> niveaux = List.of("Licence", "DUT");

    // Ajouter les attributs pour la vue
    model.addAttribute("filieres", filieres);
    model.addAttribute("professeurs", professeurs);
    model.addAttribute("niveaux", niveaux);
    return "filieres";
  }

  @GetMapping("/filieres")
  public String showFilieresPage(Model model) {
    List<Filiere> filieres = filiereRepository.findAll();
    List<User> professeurs = userRepository.findByRole(User.Role.professor);

    model.addAttribute("filieres", filieres);
    model.addAttribute("professeurs", professeurs);
    return "filieres"; // nom de ta page Thymeleaf
  }

  @PostMapping("/add")
  public String addFiliere(
      @RequestParam String name,
      @RequestParam String code_abrev,
      @RequestParam String niveau,
      @RequestParam(required = false) Integer responsable_prof_id) {

    Filiere filiere = new Filiere();
    filiere.setName(name);
    filiere.setCode_abrev(code_abrev);
    filiere.setNiveau(niveau);

    if (responsable_prof_id != null) {
      User responsable = userRepository.findById(responsable_prof_id).orElse(null);
      filiere.setResponsable(responsable);
    }

    filiereRepository.save(filiere);
    return "redirect:/filieres";
  }

}