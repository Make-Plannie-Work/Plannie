package model;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GebruikerTest {

    private static Integer gebruikersID = 0;
    private static String voornaam = "testVoornaam";
    private static String achternaam = "testAchternaam";
    private static String email = "test@email.com";
    private static String wachtwoord = "testWachtwoord";

    // Deze test controleerd of de Entity Gebruiker wordt aangemaakt, en of de benodidge getters en setters aanwezig zijn.
    @Test
    void entityGebruikerWerkt() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker();

        // Activate
        gebruiker.setGebruikersId(gebruikersID);
        gebruiker.setVoornaam(voornaam);
        gebruiker.setAchternaam(achternaam);
        gebruiker.setEmail(email);
        gebruiker.setWachtwoord(wachtwoord);

        // Assert
        assertEquals(gebruikersID,gebruiker.getGebruikersId());
        assertEquals(voornaam,gebruiker.getVoornaam());
        assertEquals(achternaam,gebruiker.getAchternaam());
        assertEquals(email,gebruiker.getEmail());
        assertEquals(wachtwoord,gebruiker.getWachtwoord());
    }
}