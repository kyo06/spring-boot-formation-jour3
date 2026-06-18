package org.formation.projet3.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

//@Data de Lombok permet d'injecter les méthodes :
// getter setter, hascode, equals toString et constructeur
@Entity
@Table(name="personne")
@Data
@Builder
@AllArgsConstructor // --> public PersonneDto(String nom, int age) { ....  }
@NoArgsConstructor
public class PersonneDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="nom", nullable = false)
    private String nom;

    @Column(name="age")
    private Integer age;

    @Column(name="est_marie")
    private Boolean estMarie = false;
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
