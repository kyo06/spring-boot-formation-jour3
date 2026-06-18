package org.formation.projet3.controllers;

import org.formation.projet3.dto.PersonneDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    private List<PersonneDto> personnes = new ArrayList<>(
            Arrays.asList(
                    PersonneDto.builder().id(1).nom("toto").age(10).build(),
                    new PersonneDto(2, "tata", 25),
                    PersonneDto.builder().id(3).nom("titi").age(30).build(),
                    PersonneDto.builder().id(4).nom("tutu").age(23).build()
            )
    );

    /*
    @GetMapping("")
    public List<PersonneDto> getPersonnes() {
        return List.of(
                PersonneDto.builder().nom("toto").age(10).build(),
                new PersonneDto("tata", 25),
                PersonneDto.builder().nom("titi").age(30).build(),
                PersonneDto.builder().nom("tutu").age(23).build()
        );
    }
    */

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
            return personnes;
        }
        return personnes.stream()
                .filter(p -> nom != null && p.getNom().equals(nom))
                .filter(p -> age != null && p.getAge() == age)
                .toList();
    }

    // --> /personnes/{id}
    //Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<PersonneDto> getPersonne(@PathVariable Integer id) {
        List<PersonneDto> listP = personnes
                .stream()
                .filter(p -> p.getId().equals(id))
                .toList();
        if(listP.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listP.get(0));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        personnes.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    public PersonneDto addPersonne(
            @RequestBody PersonneDto personneDto
    ) {
        personneDto.setId(personnes.size()+1);
        personnes.add(personneDto);
        return personneDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonneDto> addPersonne(
            @PathVariable Integer id,
            @RequestBody PersonneDto personneDtoBody
    ) {
        List<PersonneDto> listP = personnes
                .stream()
                .filter(p -> p.getId().equals(id))
                .toList();
        if(listP.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PersonneDto personneDtoFound = listP.get(0);
        personneDtoFound.setNom(personneDtoBody.getNom());
        personneDtoFound.setAge(personneDtoBody.getAge());

        return ResponseEntity.ok(personneDtoFound);
    }

}
