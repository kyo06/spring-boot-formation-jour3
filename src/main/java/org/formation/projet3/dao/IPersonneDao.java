package org.formation.projet3.dao;

import jdk.jfr.Registered;
import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonneDao {
    List<PersonneDto> findAll();

    PersonneDto findById(Integer id);

    boolean deleteById(Integer id);

    PersonneDto save(PersonneDto personneDto);
}
