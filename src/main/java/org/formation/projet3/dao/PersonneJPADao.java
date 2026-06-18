package org.formation.projet3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("PersonneJPADao")
@Transactional
public class PersonneJPADao implements IPersonneDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<PersonneDto> findAll() {
        // JPQL --> SQL Simplifié
        return em.createQuery("select p from PersonneDto p", PersonneDto.class).getResultList();
    }

    @Override
    public PersonneDto findById(Integer id) {
        return em.find(PersonneDto.class, id);
    }

    @Override
    public boolean deleteById(Integer id) {
        PersonneDto personneDto = findById(id);

        if(personneDto == null) {
            return false;
        }
        em.remove(personneDto);
        return true;
    }

    @Override
    public PersonneDto save(PersonneDto personneDto) {
        // Création
        if (personneDto.getId() == null) {
            em.persist(personneDto);
            return personneDto;
        }

        // Mise à jour
        return em.merge(personneDto);
    }
}
