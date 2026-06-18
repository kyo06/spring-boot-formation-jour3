package org.formation.projet3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

//@Data de Lombok permet d'injecter les méthodes :
// getter setter, hascode, equals toString et constructeur
@Data
@Builder
@AllArgsConstructor // --> public PersonneDto(String nom, int age) { ....  }
public class PersonneDto {
    private Integer id;
    private String nom;
    private Integer age;
    /*
    public PersonneDto(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PersonneDto that = (PersonneDto) o;
        return age == that.age && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, age);
    }

    @Override
    public String toString() {
        return "PersonneDto{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
    */
}
