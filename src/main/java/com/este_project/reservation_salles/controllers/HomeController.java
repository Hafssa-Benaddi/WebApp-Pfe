package com.este_project.reservation_salles.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // Il faut un fichier home.html dans templates
    }
}
