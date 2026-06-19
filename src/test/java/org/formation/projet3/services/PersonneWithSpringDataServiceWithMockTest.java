package org.formation.projet3.services;

import org.formation.projet3.dao.IPersonneSpringDataDao;
import org.formation.projet3.dto.PersonneDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PersonneWithSpringDataServiceWithMockTest {

    @Mock
    private IPersonneSpringDataDao personneDao;

    @InjectMocks
    PersonneWithSpringDataService personneService;

    private PersonneDto personneTest;

    @BeforeEach
    void setUp() {
        personneTest = new PersonneDto();
        personneTest.setId(1);
        personneTest.setNom("Dupont");
        personneTest.setAge(30);

        // Override with mock on Dao methods
        List<PersonneDto> personnes = List.of(personneTest);

        // lenient() permet de conserver les stubs de mock déjà créé auparavant
        // Comme le setup est appelé à chaque début de test,
        // il ne vas pas l'overrider plusieurs fois
        // (ce sera fait qu'une seule fois)
        lenient().when(personneDao.findAll())
                .thenReturn(personnes);

        lenient().when(personneDao.findAll2())
                .thenReturn(personnes);

        lenient().when(personneDao.findById(1))
                .thenReturn(Optional.of(personneTest));

        lenient().when(personneDao.findByNomAndAge("Dupont", 30))
                .thenReturn(personnes);

        lenient().when(personneDao.findWithNameAndAge("Dupont", 30))
                .thenReturn(personnes);

        lenient().when(personneDao.findWithNameAndAgeNative("Dupont", 30))
                .thenReturn(personnes);

        lenient().when(personneDao.findByNomIgnoreCase("dupont"))
                .thenReturn(personnes);

        lenient().when(personneDao.findByNomContaining("upo"))
                .thenReturn(personnes);

        lenient().when(personneDao.findByNomOrAge("Dupont", 30))
                .thenReturn(personnes);

        lenient().when(personneDao.save(any(PersonneDto.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        lenient().doNothing().when(personneDao).deleteById(anyInt());
    }

    @Test
    void testFindAll() {
        List<PersonneDto> personnes = personneService.findAll();
        assertNotNull(personnes);
        assertFalse(personnes.isEmpty());
    }

    @Test
    void testSearchAllByCriteria() {
        when(personneDao.findByNomAndAge("Dupont", 30))
                .thenReturn(List.of(personneTest));

        List<PersonneDto> personnes =
                personneService.searchAllByCriteria("Dupont", 30);

        assertNotNull(personnes);
        assertEquals(1, personnes.size());
        assertEquals("Dupont", personnes.get(0).getNom());

        verify(personneDao).findByNomAndAge("Dupont", 30);
    }

    @Test
    void testFindById() {
        PersonneDto personne = personneService.findById(1);

        assertNotNull(personne);
        assertEquals(1, personne.getId());
        assertEquals("Dupont", personne.getNom());

        verify(personneDao).findById(1);
    }

    @Test
    void testDeleteById() {
        personneService.deleteById(1);

        verify(personneDao).deleteById(1);
    }

    @Test
    void testAdd() {
        PersonneDto nouvelle = new PersonneDto();
        nouvelle.setId(2);
        nouvelle.setNom("Martin");
        nouvelle.setAge(25);

        when(personneDao.save(nouvelle))
                .thenReturn(nouvelle);

        PersonneDto saved = personneService.add(nouvelle);

        assertNotNull(saved);
        assertEquals("Martin", saved.getNom());

        verify(personneDao).save(nouvelle);
    }

    @Test
    void testUpdate() {
        personneTest.setNom("Durand");
        personneTest.setAge(35);

        when(personneDao.save(personneTest))
                .thenReturn(personneTest);

        PersonneDto updated =
                personneService.update(personneTest);

        assertNotNull(updated);
        assertEquals("Durand", updated.getNom());
        assertEquals(35, updated.getAge());

        verify(personneDao).save(personneTest);
    }
}