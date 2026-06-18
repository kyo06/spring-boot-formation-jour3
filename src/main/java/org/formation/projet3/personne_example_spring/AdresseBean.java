package org.formation.projet3.personne_example_spring;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AdresseBean {
    private String rue = "1 rue des églantines";
    private String codePostal = "06600";
    private String ville = "Antibes";
}
