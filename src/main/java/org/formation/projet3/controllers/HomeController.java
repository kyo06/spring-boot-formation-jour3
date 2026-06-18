package org.formation.projet3.controllers;

import org.formation.projet3.personne_example_spring.PersonneBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private PersonneBean personneBean;

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

    @GetMapping("/unothorized")
    public ResponseEntity<String> unothorized() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("non autorisé");
    }

    @GetMapping("/forbidden")
    public ResponseEntity<String> forbidden() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("ressource interdite");
    }

    @GetMapping(path = "/personnejson", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PersonneBean> getPersonneJSON() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personneBean);
    }

    @GetMapping(path = "/personnexml", produces = MediaType.APPLICATION_XML_VALUE)
    ResponseEntity<PersonneBean> getPersonneXML() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personneBean);
    }

}
