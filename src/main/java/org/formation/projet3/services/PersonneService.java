package org.formation.projet3.services;

import org.formation.projet3.dao.IPersonneDao;
import org.formation.projet3.dao.PersonneInMemoryDAO;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service est un composant Spring (stéréotype)
@Service
public class PersonneService {

    @Autowired
    @Qualifier("PersonneJPADao")
    private IPersonneDao personneDao;

    public List<PersonneDto> findAll() {
        return personneDao.findAll();
    }

    public List<PersonneDto> searchAllByCriteria(String nom, Integer age) {
        return personneDao.findAll().stream()
                .filter(p -> p.getNom().equals(nom))
                .filter(p -> p.getAge().equals(age))
                .toList();
    }

    public PersonneDto findById(Integer id) {
        return personneDao.findById(id);
    }

    public boolean deleteById(Integer id) {
        return personneDao.deleteById(id);
    }

    public PersonneDto add(PersonneDto personneDto) {
        personneDto.setId(null);
        return personneDao.save(personneDto);
    }

    public PersonneDto update(PersonneDto personneDto) {
        return personneDao.save(personneDto);
    }
}
