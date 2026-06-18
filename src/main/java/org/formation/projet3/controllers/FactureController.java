package org.formation.projet3.controllers;

import org.formation.projet3.dto.FactureDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final List<FactureDto> factures = new ArrayList<>(
            Arrays.asList(
                    FactureDto.builder().id(1).idClient(1).libelle("Frais de déplacement").montant(1000.0).build(),
                    new FactureDto(2, 1, "Prestation 1", 1500.0),
                    FactureDto.builder().id(3).idClient(2).libelle("Prestation 3").montant(800.0).build(),
                    FactureDto.builder().id(4).idClient(2).libelle("Prestation 4").montant(2200.0).build()
            )
    );

    /*
    @GetMapping("")
    public List<FactureDto> getFactures() {
        return List.of(
                FactureDto.builder().nom("toto").age(10).build(),
                new FactureDto("tata", 25),
                FactureDto.builder().nom("titi").age(30).build(),
                FactureDto.builder().nom("tutu").age(23).build()
        );
    }
    */

    // --> /factures
    // --> /factures?idClient=1
    // QueryParams ou RequestParams
    @GetMapping("")
    public List<FactureDto> getFactures(
            @RequestParam(required = false) Integer idClient
    ) {
        //Recherche la liste des factures qui ont le même idClient ...
        if(idClient == null) {
            return factures;
        }
        return factures.stream()
                .filter(p -> p.getIdClient().equals(idClient))
                .toList();
    }

    // --> /factures/{id}
    //Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFacture(@PathVariable Integer id) {
        List<FactureDto> listP = factures
                .stream()
                .filter(f -> f.getId().equals(id))
                .toList();
        if(listP.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(listP.get(0));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Integer id) {
        factures.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    public FactureDto addFacture(
            @RequestBody FactureDto FactureDto
    ) {
        FactureDto.setId(factures.size()+1);
        factures.add(FactureDto);
        return FactureDto;
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFacture(
            @PathVariable Integer id,
            @RequestBody FactureDto FactureDtoBody
    ) {
        List<FactureDto> listP = factures
                .stream()
                .filter(f -> f.getId().equals(id))
                .toList();
        if(listP.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        FactureDto factureDtoFound = listP.get(0);
        factureDtoFound.setIdClient(factureDtoFound.getIdClient());
        factureDtoFound.setLibelle(factureDtoFound.getLibelle());
        factureDtoFound.setMontant(factureDtoFound.getMontant());

        return ResponseEntity.ok(factureDtoFound);
    }

}
