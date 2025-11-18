package com.Eventify.Eventify.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class organisationController {
    @GetMapping("/organiser")
    public String organisation() {
        return "organisation"; // retourne login.html dans templates
    }
}
