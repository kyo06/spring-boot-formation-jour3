package org.formation.projet3.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @GetMapping("/bye")
    public String bye() {
        return "Goodbye!!!";
    }

    @GetMapping("/name")
    public String name() {
        return "Julien";
    }

    @GetMapping("/name2")
    public ResponseEntity<String> name2() {
        return ResponseEntity.ok("Julien");
    }

    @GetMapping("/created")
    public ResponseEntity<String> created() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Created");
    }

    @GetMapping("/notfound")
    public ResponseEntity<String> notFound() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Ressource introuvable");
    }

}
