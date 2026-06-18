package org.formation.projet3.personne;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("singleton")
//@Scope("prototype")
@Data
public class PersonneBean {
    private String name;
    @Autowired
    private AdresseBean adresseBean;

    public PersonneBean() {
        System.out.println("PersonneBean créé !");
    }
}
