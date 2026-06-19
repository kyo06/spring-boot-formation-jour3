package org.formation.projet3.services;

import org.formation.projet3.dto.PersonneDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonneWithSpringDataServiceTest {

    @Autowired
    PersonneWithSpringDataService personneService;

    private PersonneDto personneTest;

    @BeforeEach
    void setUp() {
        personneTest = new PersonneDto();
        personneTest.setNom("Dupont");
        personneTest.setAge(30);

        personneTest = personneService.add(personneTest);
    }

    @AfterEach
    void tearDown() {
        for(PersonneDto personneDto : personneService.findAll()) {
            try {
                personneService.deleteById(personneDto.getId());
            } catch (Exception ignored) {
            }
        }
        personneTest = null;
    }

    @Test
    void testFindAll() {
        List<PersonneDto> personnes = personneService.findAll();
        assertNotNull(personnes);
        assertFalse(personnes.isEmpty());
    }

    @Test
    void testSearchAllByCriteria() {
        List<PersonneDto> personnes =
                personneService.searchAllByCriteria("Dupont", 30);
        assertNotNull(personnes);
        assertFalse(personnes.isEmpty());
        assertEquals("Dupont", personnes.get(0).getNom());
        assertEquals(30, personnes.get(0).getAge());
    }

    @Test
    void testFindById() {
        PersonneDto personne =
                personneService.findById(personneTest.getId());

        assertNotNull(personne);
        assertEquals(personneTest.getId(), personne.getId());
        assertEquals("Dupont", personne.getNom());
    }

    @Test
    void testDeleteById() {
        Integer id = personneTest.getId();
        personneService.deleteById(id);

        PersonneDto personne = personneService.findById(id);
        assertNull(personne);
    }

    @Test
    void testAdd() {
        PersonneDto p = new PersonneDto();
        p.setNom("Martin");
        p.setAge(25);
        PersonneDto saved = personneService.add(p);

        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals("Martin", saved.getNom());
    }

    @Test
    void testUpdate() {
        personneTest.setNom("Durand");
        personneTest.setAge(35);

        PersonneDto updated =
                personneService.update(personneTest);

        assertNotNull(updated);
        assertEquals("Durand", updated.getNom());
        assertEquals(35, updated.getAge());
    }
}