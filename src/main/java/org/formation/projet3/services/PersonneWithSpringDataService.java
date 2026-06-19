package org.formation.projet3.services;

import org.formation.projet3.dao.IPersonneSpringDataDao;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service est un composant Spring (stéréotype)
@Service
@Primary
public class PersonneWithSpringDataService implements IPersonneService {

    @Autowired
    private IPersonneSpringDataDao personneDao;

    public List<PersonneDto> findAll() {
        return personneDao.findAll2();
    }

    public List<PersonneDto> searchAllByCriteria(String nom, Integer age) {
        return personneDao.findByNomAndAge(nom, age);
    }

    public PersonneDto findById(Integer id) {
        return personneDao.findById(id).orElse(null);
    }

    public boolean deleteById(Integer id) {
        personneDao.deleteById(id);
        return true;
    }

    public PersonneDto add(PersonneDto personneDto) {
        personneDto.setId(null);
        return personneDao.save(personneDto);
    }

    public PersonneDto update(PersonneDto personneDto) {
        return personneDao.save(personneDto);
    }
}
