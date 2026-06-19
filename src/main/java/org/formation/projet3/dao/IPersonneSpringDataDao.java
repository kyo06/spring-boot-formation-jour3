package org.formation.projet3.dao;

import org.formation.projet3.dto.PersonneDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IPersonneSpringDataDao extends CrudRepository<PersonneDto, Integer> {

    default List<PersonneDto> findAll2() {
        List<PersonneDto> l = new ArrayList<>();
        findAll().forEach(l::add);
        return l;
    }

    // Méthode 1
    // JPQL Query
    @Query("""
           SELECT p
           FROM PersonneDto p
           WHERE p.nom = :nom
             AND p.age = :age
           """) // Requête JPQL
    List<PersonneDto> findWithNameAndAge(
            @Param("nom") String nom,
            @Param("age") Integer age);


    // Méthode 2
    // Native Query -> Pure SQL
    @Query(
            value = """
                SELECT *
                FROM personne
                WHERE nom = :nom
                  AND age = :age
                """,
            nativeQuery = true
    )
    List<PersonneDto> findWithNameAndAgeNative(
            @Param("nom") String nom,
            @Param("age") Integer age);


    // Méthode 3
    // Query methods
    // https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

    List<PersonneDto> findByNomAndAge(String nom, Integer age);

    List<PersonneDto> findByNomIgnoreCase(String nom);

    List<PersonneDto> findByNomContaining(String nom);

    List<PersonneDto> findByNomOrAge(String nom, Integer age);
}
