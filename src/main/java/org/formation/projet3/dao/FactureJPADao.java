package org.formation.projet3.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("FactureJPADao")
@Transactional
public class FactureJPADao implements IFactureDAO {

    @PersistenceContext
    private EntityManager em;

    public List<FactureDto> findAll() {
        // JPQL --> SQL Simplifié
        return em.createQuery("select f from FactureDto f", FactureDto.class).getResultList();
    }

    public FactureDto findById(Integer id) {
        return em.find(FactureDto.class, id);
    }

    public boolean deleteById(Integer id) {
        FactureDto factureDto = findById(id);

        if(factureDto == null) {
            return false;
        }
        em.remove(factureDto);
        return true;
    }

    public FactureDto save(FactureDto factureDto) {
        // Création
        if (factureDto.getId() == null) {
            em.persist(factureDto);
            return factureDto;
        }

        // Mise à jour
        return em.merge(factureDto);
    }
}
