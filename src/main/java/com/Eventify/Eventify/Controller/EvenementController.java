package com.Eventify.Eventify.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EvenementController {
    @GetMapping("/Événements")
    public String event() {
        return "Événement";

}
}
