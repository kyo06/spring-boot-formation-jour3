package org.formation.projet3.controllers;

import org.formation.projet3.dto.PersonneDto;
import org.formation.projet3.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// @RestController est un composant Spring (stéréotype)
@RestController
@RequestMapping("/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    // --> /personnes
    // --> /personnes?nom=Dupont&age=15
    // QueryParams ou RequestParams
    @GetMapping("")
    public List<PersonneDto> getPersonnes(
            @RequestParam(required = false) String nom,
            @RequestParam(required = false) Integer age
    ) {
        //Recherche la liste des personnes qui ont le nom ...
        if(nom == null && age == null) {
            return personneService.findAll();
        }
        return personneService.searchAllByCriteria(nom, age);
    }

    // --> /personnes/{id}
    //Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<PersonneDto> getPersonne(@PathVariable Integer id) {
        PersonneDto personneDto = personneService.findById(id);
        if(personneDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(personneDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Integer id) {
        personneService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    public PersonneDto addPersonne(
            @RequestBody PersonneDto personneDto
    ) {
        return personneService.add(personneDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonneDto> updatePersonne(
            @PathVariable Integer id,
            @RequestBody PersonneDto personneDtoBody
    ) {
        personneDtoBody.setId(id);
        PersonneDto personneDtoFound = personneService.update(personneDtoBody);
        if(personneDtoFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personneDtoFound);
    }

}
