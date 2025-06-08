package com.este_project.reservation_salles.controllers;

import com.este_project.reservation_salles.models.User;
import com.este_project.reservation_salles.repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Page d'accès admin avec boutons "login" et "register"
    @GetMapping("/admin-access")
    public String showAdminAccessPage() {
        return "admin_access";
    }

    // Page d'accès prosesseur "
    @GetMapping("/professeur-acces")
    public String showprofesseurAccessPage() {
        return "professeur_acces";
    }

    // Formulaire de connexion
    @GetMapping("/login")
    public String showLoginForm(@RequestParam(value = "role", required = false) String role, Model model) {
        model.addAttribute("selectedRole", role);
        return "login";
    }

    // Traitement de la connexion
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session,
                        Model model) {

        Optional<User> optionalUser = userRepository.findByEmailAcademic(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            if (user.getPassword().equals(password)) {
                if (!user.getIsActive()) {
                    model.addAttribute("error", "Compte inactif.");
                    return "login";
                }

                session.setAttribute("user", user);

                switch (user.getRole()) {
                    case admin:
                        return "redirect:/admin/dashboard";
                    case professor:
                        return "redirect:/professors/dashboard";
                    default:
                        model.addAttribute("error", "Rôle non reconnu.");
                        return "login";
                }
            } else {
                model.addAttribute("error", "Mot de passe incorrect.");
            }
        } else {
            model.addAttribute("error", "Utilisateur non trouvé.");
        }

        return "login";
    }

    // Formulaire d'inscription admin
    @GetMapping("/register")
    public String showRegisterForm() {
        return "admin_register";
    }

    // Traitement de l'inscription admin
    @PostMapping("/register")
    public String registerAdmin(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam String email,
                                @RequestParam String password,
                                @RequestParam String department,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        Optional<User> existingUser = userRepository.findByEmailAcademic(email);
        if (existingUser.isPresent()) {
            model.addAttribute("error", "Email déjà utilisé.");
            return "admin_register";
        }

        User admin = new User();
        admin.setFirstName(firstName);
        admin.setLastName(lastName);
        admin.setEmailAcademic(email);
        admin.setPassword(password);
        admin.setDepartment(department);
        admin.setRole(User.Role.admin);
        admin.setIsActive(true);

        userRepository.save(admin);
        redirectAttributes.addFlashAttribute("message", "Compte admin créé avec succès. Vous pouvez vous connecter.");
        return "redirect:/login?role=admin";
    }

    // Espace admin (dashboard)
    @GetMapping("/admin/dashboard")
    public String adminDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.admin) {
            return "redirect:/login";
        }
        return "espace_admin";
    }

    // Espace professeur (dashboard)
    @GetMapping("/professors/dashboard")
    public String professorDashboard(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null || user.getRole() != User.Role.professor) {
            return "redirect:/login";
        }
        return "espace_professeur";
    }

    // Déconnexion
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}
