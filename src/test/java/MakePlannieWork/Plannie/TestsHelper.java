package MakePlannieWork.Plannie;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * TestsHelper kan worden aangeroepen door alle tests, om herhaling van code te voorkomen.
 * Hij kan testgebruikers aanmaken, registeren, inloggen, verwijderen en teruggeven.
 *
 * In de toekomst zal het behalve gebruikers, ook de groepen en reizen kunnen aanmaken om mee te testen.
 */

public class TestsHelper {

    private static final String VOORNAAM = "testVoornaam";
    private static final String ACHTERNAAM = "testAchternaam";
    private static final String EMAIL = ".testing@test.com";
    private static final String WACHTWOORD = "testWachtwoord";

    private ArrayList<Gebruiker> testGebruikers = new ArrayList<>();
    private GebruikerRepository gebruikerRepository;
    private PasswordEncoder passwordEncoder;
    private WebDriverWait wacht;
    private WebDriver driver;

    // Constructors
    public TestsHelper(WebDriver driver, GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder) {
        this.driver = driver;
        this.gebruikerRepository = gebruikerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TestsHelper(WebDriver driver) {
        this(driver, null, null);
    }

    // Wachten tot de volgende pagina geladen is:
    public void wachtOpElement(String idElement) {
        if (this.wacht == null) {
            this.wacht = new WebDriverWait(this.driver, 10);
        }
        this.wacht.until(ExpectedConditions.visibilityOfElementLocated(By.id(idElement)));
    }

    public void wachtOpTitel(String titel) {
        if (this.wacht == null) {
            this.wacht = new WebDriverWait(this.driver, 10);
        }
        this.wacht.until(ExpectedConditions.titleIs(titel));
    }

    // Test Gebruiker inloggen:
    public void inloggen() {
        inloggen(0);
    }

    public void inloggen(int index) {
        Gebruiker testGebruiker = testGebruikers.get(index);
        driver.findElement(By.name("username")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("password")).sendKeys(testGebruiker.getWachtwoord() + Keys.RETURN);
    }

    // Test Gebruikers aanmaken:
    public void maakTestGebruikers(int aantal) {
        this.testGebruikers.clear();
        for (int i = 0; i < aantal; i++) {
            Gebruiker testGebruiker = new Gebruiker();
            testGebruiker.setVoornaam(VOORNAAM + i);
            testGebruiker.setAchternaam(ACHTERNAAM + i);
            testGebruiker.setEmail(VOORNAAM + i + EMAIL);
            testGebruiker.setWachtwoord(WACHTWOORD + i);
            this.testGebruikers.add(testGebruiker);
        }
    }

    public void maakTestGebruiker() {
        maakTestGebruikers(1);
    }

    // Test Gebruikers geven: Je kan alle gebruikers, of 1 opvragen.
    public ArrayList<Gebruiker> geefTestGebruikers() {
        return this.testGebruikers;
    }

    public Gebruiker geefTestGebruiker() {
        return geefTestGebruiker(0);
    }

    public Gebruiker geefTestGebruiker(int index) {
        return this.testGebruikers.get(index);
    }

    // Test Gebruikers registeren
    public void registreerTestGebruikers() {
        int index = 0;
        for (Gebruiker testGebruiker : testGebruikers) {
            registreerTestGebruiker(index);
            index++;
        }
    }

    public void registreerTestGebruiker(int index) {
        Gebruiker testGebruiker = this.testGebruikers.get(index);
        String wachtwoord = testGebruiker.getWachtwoord();
        String encodedWachtwoord = passwordEncoder.encode(wachtwoord);

        testGebruiker.setWachtwoord(encodedWachtwoord);
        testGebruiker.setTrancientWachtwoord(encodedWachtwoord);
        gebruikerRepository.save(testGebruiker);

        testGebruiker.setWachtwoord(wachtwoord);
    }

    // Test Gebruikers verwijderen uit database
    public void verwijderTestGebruikersUitDatabase() {
        int index = 0;
        for (Gebruiker testGebruiker : testGebruikers) {
            verwijderTestGebruikerUitDatabase(index);
            index++;
        }
    }

    public void verwijderTestGebruikerUitDatabase(int index) {
        Gebruiker testGebruiker = this.testGebruikers.get(index);
        List<Gebruiker> testGebruikersInDatabase = gebruikerRepository.findGebruikersByEmail(testGebruiker.getEmail());
        if (!testGebruikersInDatabase.isEmpty()) {
            gebruikerRepository.deleteAll(testGebruikersInDatabase);
        }
    }
}
