package org.formation.projet3.services;

import org.formation.projet3.dao.FactureInMemoryDAO;
import org.formation.projet3.dto.FactureDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service est un composant Spring (stéréotype)
@Service
public class FactureService {

    @Autowired
    private FactureInMemoryDAO factureDao;

    public List<FactureDto> findAll() {
        return factureDao.findAll();
    }

    public List<FactureDto> searchAllByCriteria(Integer idClient) {
        return factureDao.findAll().stream()
                .filter(p -> p.getIdClient().equals(idClient))
                .toList();
    }

    public FactureDto findById(Integer id) {
        return factureDao.findById(id);
    }

    public boolean deleteById(Integer id) {
        return factureDao.deleteById(id);
    }

    public FactureDto add(FactureDto factureDto) {
        factureDto.setId(null);
        return factureDao.save(factureDto);
    }

    public FactureDto update(FactureDto factureDto) {
        return factureDao.save(factureDto);
    }
}
