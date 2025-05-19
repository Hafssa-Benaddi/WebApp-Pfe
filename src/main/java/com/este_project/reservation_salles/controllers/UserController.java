package com.este_project.reservation_salles.controllers;

import com.este_project.reservation_salles.models.User;
import com.este_project.reservation_salles.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professors")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String professorView(Model model) {
        List<User> professors = userRepository.findByRole(User.Role.professor);
        model.addAttribute("professors", professors);
        return "professors";
    }

    @PostMapping("/upload")
    @Transactional
    public String uploadCsv(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Le fichier est vide !");
            return "redirect:/professors";
        }

        List<String> errorMessages = new ArrayList<>();
        int successCount = 0;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] data = line.split(",");
                if (data.length < 9) {
                    errorMessages.add("Ligne ignorée (données incomplètes) : " + line);
                    continue;
                }

                String firstName = data[0].trim();
                String lastName = data[1].trim();
                String email = data[2].trim();
                String password = data[3].trim();
                String roleStr = data[4].trim().toLowerCase();
                String officeNumber = data[5].trim();
                String speciality = data[6].trim();
                String department = data[7].trim();
                String isActiveStr = data[8].trim();

                // Validation de base
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()
                        || roleStr.isEmpty() || department.isEmpty()) {
                    errorMessages.add("Champs obligatoires manquants pour : " + email);
                    continue;
                }

                // Le rôle doit être "professor"
                if (!roleStr.equals("professor")) {
                    errorMessages.add("Rôle invalide pour l'utilisateur " + email + " (doit être 'professor')");
                    continue;
                }

                // Email unique
                Optional<User> existing = userRepository.findByEmailAcademic(email);
                if (existing.isPresent()) {
                    errorMessages.add("Email déjà utilisé : " + email);
                    continue;
                }

                // Création de l'utilisateur
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmailAcademic(email);
                user.setPassword(password);
                user.setRole(User.Role.professor);
                user.setOfficeNumber(officeNumber);
                user.setSpeciality(speciality);
                user.setDepartment(department);
                user.setIsActive(Boolean.parseBoolean(isActiveStr));

                userRepository.save(user);
                successCount++;
            }

            userRepository.flush();

            redirectAttributes.addFlashAttribute("message",
                    "Importation terminée : " + successCount + " professeurs ajoutés.");
            if (!errorMessages.isEmpty()) {
                redirectAttributes.addFlashAttribute("errors", errorMessages);
            }

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'importation.");
        }

        return "redirect:/professors";
    }

    @PostMapping("/add")
    @Transactional
    public String addProfessor(@RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam(required = false) String officeNumber,
            @RequestParam(required = false) String speciality,
            @RequestParam String department,
            RedirectAttributes redirectAttributes) {

        Optional<User> existing = userRepository.findByEmailAcademic(email);
        if (existing.isPresent()) {
            redirectAttributes.addFlashAttribute("error", "Email déjà utilisé.");
            return "redirect:/professors";
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmailAcademic(email);
        user.setPassword(password);
        user.setRole(User.Role.professor);
        user.setOfficeNumber(officeNumber);
        user.setSpeciality(speciality);
        user.setDepartment(department);
        user.setIsActive(true);

        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Professeur ajouté avec succès.");
        return "redirect:/professors";
    }

}
