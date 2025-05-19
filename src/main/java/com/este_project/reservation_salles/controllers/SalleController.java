package com.este_project.reservation_salles.controllers;

import com.este_project.reservation_salles.models.Salle;
import com.este_project.reservation_salles.repositories.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/salles")
public class SalleController {

    @Autowired
    private SalleRepository salleRepository;

    @GetMapping
    public String listSalles(Model model) {
        List<Salle> salles = salleRepository.findAll();
        model.addAttribute("salles", salles);
        return "salles";
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<String> addSalle(@RequestBody Salle salle) {
        try {
            salleRepository.save(salle);
            return ResponseEntity.ok("Salle ajoutée avec succès !");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur lors de l'ajout de la salle: " + e.getMessage());
        }
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, Model model) {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    Salle salle = new Salle();
                    salle.setNumeroSalle(data[0]);
                    salle.setCapacity(Integer.parseInt(data[1]));
                    salle.setType(data[2]);
                    salleRepository.save(salle);
                }
            }
            model.addAttribute("message", "Importation réussie");
        } catch (Exception e) {
            model.addAttribute("error", "Erreur d'importation : " + e.getMessage());
        }
        return "redirect:/salles";
    }
}
