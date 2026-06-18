package org.formation.projet3.dao;

import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.FactureDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @Repository est un composant Spring (stéréotype)
@Repository
public class FactureInMemoryDAO {

    private final List<FactureDto> factures = new ArrayList<>(
            Arrays.asList(
                    FactureDto.builder().id(1).idClient(1).libelle("Frais de déplacement").montant(1000.0).build(),
                    new FactureDto(2, 1, "Prestation 1", 1500.0),
                    FactureDto.builder().id(3).idClient(2).libelle("Prestation 3").montant(800.0).build(),
                    FactureDto.builder().id(4).idClient(2).libelle("Prestation 4").montant(2200.0).build()
            )
    );

    public List<FactureDto> findAll() {
        return factures;
    }

    public FactureDto findById(Integer id) {
        return factures.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Integer id) {
        return factures.removeIf(p -> p.getId().equals(id));
    }

    public FactureDto save(FactureDto factureDto) {
        if(factureDto.getId() == null) {
            //Sauvegarde
            factureDto.setId(factures.size() + 1);
            factures.add(factureDto);

            return factureDto;
        }

        //Update
        FactureDto toUpdate = this.findById(factureDto.getId());
        if(toUpdate != null) {
            toUpdate.setIdClient(factureDto.getIdClient());
            toUpdate.setLibelle(factureDto.getLibelle());
            toUpdate.setMontant(factureDto.getMontant());
        }
        return toUpdate;
    }
}
