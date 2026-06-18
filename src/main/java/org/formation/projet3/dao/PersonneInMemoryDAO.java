package org.formation.projet3.dao;

import org.formation.projet3.dto.PersonneDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// @Repository est un composant Spring (stéréotype)
@Repository
public class PersonneInMemoryDAO {

    private List<PersonneDto> personnes = new ArrayList<>(
            Arrays.asList(
                    PersonneDto.builder().id(1).nom("toto").age(10).build(),
                    new PersonneDto(2, "tata", 25),
                    PersonneDto.builder().id(3).nom("titi").age(30).build(),
                    PersonneDto.builder().id(4).nom("tutu").age(23).build()
            )
    );

    public List<PersonneDto> findAll() {
        return personnes;
    }

    public PersonneDto findById(Integer id) {
        return personnes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(Integer id) {
        return personnes.removeIf(p -> p.getId().equals(id));
    }

    public PersonneDto save(PersonneDto personneDto) {
        if(personneDto.getId() == null) {
            //Sauvegarde
            personneDto.setId(personnes.size() + 1);
            personnes.add(personneDto);

            return personneDto;
        }

        //Update
        PersonneDto toUpdate = this.findById(personneDto.getId());
        if(toUpdate != null) {
            toUpdate.setNom(personneDto.getNom());
            toUpdate.setAge(personneDto.getAge());
        }
        return toUpdate;
    }
}
