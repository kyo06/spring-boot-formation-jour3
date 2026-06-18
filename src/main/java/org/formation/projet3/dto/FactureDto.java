package org.formation.projet3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FactureDto {

    private Integer id;
    private Integer idClient;
    private String libelle;
    private Double montant;
}
