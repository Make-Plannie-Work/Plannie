package MakePlannieWork.Plannie.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class GebruikerTest {

    private static Integer gebruikersId = 0;
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
        gebruiker.setGebruikersId(gebruikersId);
        gebruiker.setVoornaam(voornaam);
        gebruiker.setAchternaam(achternaam);
        gebruiker.setEmail(email);
        gebruiker.setWachtwoord(wachtwoord);

        // Assert
        assertEquals(gebruikersId,gebruiker.getGebruikersId());
        assertEquals(voornaam,gebruiker.getVoornaam());
        assertEquals(achternaam,gebruiker.getAchternaam());
        assertEquals(email,gebruiker.getEmail());
        assertEquals(wachtwoord,gebruiker.getWachtwoord());
    }
}