package org.formation.projet3.services;

import org.formation.projet3.dao.IFactureDAO;
import org.formation.projet3.dao.IFactureSpringDataDAO;
import org.formation.projet3.dto.FactureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service est un composant Spring (stéréotype)
@Service
@Primary
public class FactureWithSpringDataService implements IFactureService {

    @Autowired
    private IFactureSpringDataDAO factureDao;

    public List<FactureDto> findAll() {
        List<FactureDto> l = new ArrayList<>();
        factureDao.findAll().forEach(l::add);
        return l;
    }

    @Override
    public List<FactureDto> findByClientId(Integer idClient) {
        return factureDao.findByClientId(idClient);
    }

    public List<FactureDto> searchAllByCriteria(Integer idClient) {
        return factureDao.findByClientId(idClient);
    }

    public FactureDto findById(Integer id) {
        return factureDao.findById(id).orElse(null);
    }

    public boolean deleteById(Integer id) {
        factureDao.deleteById(id);
        return true;
    }

    public FactureDto add(FactureDto factureDto) {
        factureDto.setId(null);
        return factureDao.save(factureDto);
    }

    public FactureDto update(FactureDto factureDto) {
        return factureDao.save(factureDto);
    }
}
