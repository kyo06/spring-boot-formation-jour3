package org.formation.projet3.services;

import org.formation.projet3.dto.FactureDto;

import java.util.List;

public interface IFactureService {
    List<FactureDto> findAll();

    List<FactureDto> searchAllByCriteria(Integer idClient);

    FactureDto findById(Integer id);

    boolean deleteById(Integer id);

    FactureDto add(FactureDto factureDto);

    FactureDto update(FactureDto factureDto);
}
