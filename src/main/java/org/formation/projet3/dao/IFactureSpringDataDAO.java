package org.formation.projet3.dao;

import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFactureSpringDataDAO extends CrudRepository<FactureDto, Integer> {

    List<FactureDto> findByIdClient(Integer idClient);
}
