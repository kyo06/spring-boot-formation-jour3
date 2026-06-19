package org.formation.projet3.services;

import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StatService {

    private final List<FactureDto> factures = new ArrayList<>(
            Arrays.asList(
                    FactureDto.builder().id(1).client(PersonneDto.builder().id(1).nom("toto").age(10).build()).libelle("Frais de déplacement").montant(1000.0).build(),
                    new FactureDto(2, PersonneDto.builder().id(2).nom("tata").age(10).build(), "Prestation 1", 1500.0),
                    FactureDto.builder().id(3).client(PersonneDto.builder().id(1).nom("toto").age(10).build()).libelle("Prestation 3").montant(800.0).build(),
                    FactureDto.builder().id(4).client(PersonneDto.builder().id(3).nom("tutu").age(10).build()).libelle("Prestation 4").montant(2200.0).build()
            )
    );

    public double getTotal() {
        return factures.stream()
                .mapToDouble(FactureDto::getMontant)
                .reduce(0, (a,b) -> a + b);
    }

    public int nbPrestations() {
        //Voir dans libelle les prestations
        return 0;
    }

    public int nbUniqueClients() {
        return 0;
    }
}
