package org.formation.projet3.services;

import org.formation.projet3.dto.PersonneDto;

import java.util.List;

public interface IPersonneService {
    List<PersonneDto> findAll();

    List<PersonneDto> searchAllByCriteria(String nom, Integer age);

    PersonneDto findById(Integer id);

    boolean deleteById(Integer id);

    PersonneDto add(PersonneDto personneDto);

    PersonneDto update(PersonneDto personneDto);
}
