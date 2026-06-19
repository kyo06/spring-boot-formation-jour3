package org.formation.projet3.services;

import org.formation.projet3.dao.FactureInMemoryDAO;
import org.formation.projet3.dao.IFactureDAO;
import org.formation.projet3.dao.IPersonneDao;
import org.formation.projet3.dao.IPersonneSpringDataDao;
import org.formation.projet3.dto.FactureDto;
import org.formation.projet3.dto.PersonneDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service est un composant Spring (stéréotype)
@Service
public class FactureService implements IFactureService {

    @Autowired
    @Qualifier("FactureJPADao")
    private IFactureDAO factureDao;

    @Override
    public List<FactureDto> findAll() {
        return factureDao.findAll();
    }

    @Override
    public List<FactureDto> findByClientId(Integer idClient) {
        return factureDao.findAll().stream()
                .filter(p -> p.getClient().getId().equals(idClient))
                .toList();
    }

    @Override
    public List<FactureDto> searchAllByCriteria(Integer idClient) {
        return findByClientId(idClient);
    }

    @Override
    public FactureDto findById(Integer id) {
        return factureDao.findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        return factureDao.deleteById(id);
    }

    @Override
    public FactureDto add(FactureDto factureDto) {
        factureDto.setId(null);
        return factureDao.save(factureDto);
    }

    @Override
    public FactureDto update(FactureDto factureDto) {
        return factureDao.save(factureDto);
    }
}
