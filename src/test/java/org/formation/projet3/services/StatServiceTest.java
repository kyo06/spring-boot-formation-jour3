package org.formation.projet3.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatServiceTest {

    StatService statService;

    @BeforeEach
    public void setup() {
        statService = new StatService();
    }

    @Test
    public void testGetTotal() {
        double montantObtenu = statService.getTotal();
        double montantAttendu = 5500;
        assertEquals(montantObtenu, montantAttendu, "montant total doit être égale à " + montantAttendu);
    }

    @Test
    public void testNbPrestations() {
        //Voir dans libelle les prestations
        double nombreObtenu = statService.nbPrestations();
        double nombreAttendu = 3;
        assertEquals(nombreObtenu, nombreAttendu, "doit être égale à " + nombreAttendu);
    }

    @Test
    public void testnbUniqueClients() {
        double nombreObtenu = statService.nbUniqueClients();
        double nombreAttendu = 3;
        assertEquals(nombreObtenu, nombreAttendu, "doit être égale à " + nombreAttendu);
    }


}
