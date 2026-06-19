package org.formation.projet3.controllers;

import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.services.IFactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    @Autowired
    private IFactureService factureService;

    // --> /factures
    // --> /factures?idClient=1
    // QueryParams ou RequestParams
    @GetMapping("")
    public List<FactureDto> getFactures(
            @RequestParam(required = false) Integer idClient
    ) {
        //Recherche la liste des factures qui ont le même idClient ...
        if(idClient == null) {
            return factureService.findAll();
        }
        return factureService.searchAllByCriteria(idClient);
    }

    // --> /factures/{id}
    //Path Variable
    @GetMapping("/{id}")
    public ResponseEntity<FactureDto> getFacture(@PathVariable Integer id) {
        FactureDto factureDto = factureService.findById(id);
        if(factureDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(factureDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Integer id) {
        factureService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("")
    public FactureDto addFacture(
            @RequestBody FactureDto factureDto
    ) {
        return factureService.add(factureDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactureDto> updateFacture(
            @PathVariable Integer id,
            @RequestBody FactureDto factureDtoBody
    ) {
        factureDtoBody.setId(id);
        FactureDto factureDtoFound = factureService.update(factureDtoBody);
        if(factureDtoFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factureDtoFound);
    }

}
