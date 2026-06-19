package org.formation.projet3.dao;

import org.formation.projet3.dto.FactureDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFactureSpringDataDAO extends CrudRepository<FactureDto, Integer> {

    List<FactureDto> findByClientId(Integer idClient);
}
