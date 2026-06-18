package org.formation.projet3.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World!";
    }

    @GetMapping("/bonjour")
    public String bonjour() {
        return "Bonjour!!!";
    }

}
