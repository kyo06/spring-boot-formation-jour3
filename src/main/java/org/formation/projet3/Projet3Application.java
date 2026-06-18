package org.formation.projet3;

import org.formation.projet3.personne.PersonneBean;
import org.formation.projet3.writer.IWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @ComponentScan(org.formation.projet3 + org.formation.projet3.*)

public class Projet3Application {

    public static void main(String[] args) {
        SpringApplication.run(Projet3Application.class, args);
        /*
        var context = SpringApplication.run(Projet3Application.class, args);

        PersonneBean personne = context.getBean(PersonneBean.class);
        PersonneBean personne2 = context.getBean(PersonneBean.class);
        System.out.println(personne);
        System.out.println(personne.getAdresseBean());

        IWriter writer = context.getBean("xmlwriter",  IWriter.class);
        writer.write(personne);

        IWriter writer2 = context.getBean(IWriter.class);
        writer2.write(personne);
        */
    }

}
