package model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;



class GebruikerTest {

    private static Integer gebruikersID = 0;
    private static String voornaam = "testVoornaam";
    private static String achternaam = "testAchternaam";
    private static String email = "test@email.com";
    private static String wachtwoord = "testWachtwoord";

    // Deze test controleerd of de Entity Gebruiker wordt aangemaakt, en of de benodidge getters en setters aanwezig zijn.
    @Test
    void gebruikerWerkt() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker();

        // Activate
        gebruiker.setGebruikersId(gebruikersID);
        gebruiker.setVoornaam(voornaam);
        gebruiker.setAchternaam(achternaam);
        gebruiker.setEmail(email);
        gebruiker.setWachtwoord(wachtwoord);

        // Assert
        Assert.assertEquals(gebruikersID,gebruiker.getGebruikersId());
        Assert.assertEquals(voornaam,gebruiker.getVoornaam());
        Assert.assertEquals(achternaam,gebruiker.getAchternaam());
        Assert.assertEquals(email,gebruiker.getEmail());
        Assert.assertEquals(wachtwoord,gebruiker.getWachtwoord());
    }
}