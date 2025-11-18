package com.Eventify.Eventify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    @GetMapping("/profile")
    public String profile() { // méthode en minuscules
        return "Profile"; // Thymeleaf cherchera templates/Profile.html
    }
}
