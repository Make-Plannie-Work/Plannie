package MakePlannieWork.Plannie.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroepTest {
    
    private static Integer groepId = 0;
    private static String groepsNaam = "testNaam";

    // Deze test controleerd of de Entity Groep wordt aangemaakt, en of de benodidge getters en setters aanwezig zijn.
    @Test
    void entityGebruikerWerkt() {
        // Arrange
        Groep groep = new Groep();
        Gebruiker groepsLid = new Gebruiker();
        groepsLid.setVoornaam("GroepsLid1");
        Set<Gebruiker> leden = new HashSet<>();
        leden.add(groepsLid);

        // Activate
        groep.setGroepId(groepId);
        groep.setGroepsNaam(groepsNaam);
        groep.setGroepsleden(leden);

        // Assert
        assertEquals(groepId,groep.getGroepId());
        assertEquals(groepsNaam,groep.getGroepsNaam());
        assertEquals(groepsLid.getVoornaam(),groep.getGroepsleden().iterator().next().getVoornaam());
    }
}