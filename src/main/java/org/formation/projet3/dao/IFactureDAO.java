package org.formation.projet3.dao;

import org.formation.projet3.dto.FactureDto;

import java.util.List;

public interface IFactureDAO {
    List<FactureDto> findAll();

    FactureDto findById(Integer id);

    List<FactureDto> findByClientId(Integer idClient);

    boolean deleteById(Integer id);

    FactureDto save(FactureDto factureDto);
}
