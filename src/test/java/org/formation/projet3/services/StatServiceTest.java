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


}
