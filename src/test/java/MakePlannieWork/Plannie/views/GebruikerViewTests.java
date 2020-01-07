package MakePlannieWork.Plannie.views;

import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GebruikerViewTests {

    private TestsHelper testsHelper;
    private WebDriver driver;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.testsHelper = new TestsHelper(this.driver, this.gebruikerRepository, this.passwordEncoder);
    }

    @After
    public void tearDown() {

        this.testsHelper.verwijderTestGebruikersUitDatabase();
        this.driver.quit();
        this.driver = null;
        this.testsHelper = null;
    }

    @Test
    public void testRegistreerCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");
        this.testsHelper.maakTestGebruikers(1);
        Gebruiker testGebruiker = testsHelper.geefTestGebruiker();

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(testGebruiker.getVoornaam());
        driver.findElement(By.name("achternaam")).sendKeys(testGebruiker.getAchternaam());
        driver.findElement(By.name("email")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("wachtwoord")).sendKeys(testGebruiker.getWachtwoord());
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(testGebruiker.getWachtwoord());
        driver.findElement(By.id("registreer")).click();
        this.testsHelper.wachtOpTitel("Welkom bij Plannie");

        // Assert
        assertEquals("Welkom bij Plannie",driver.getTitle());
    }

    @Test
    public void testRegistreerFout() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");
        this.testsHelper.maakTestGebruiker();
        Gebruiker testGebruiker = testsHelper.geefTestGebruiker();

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(testGebruiker.getVoornaam());
        driver.findElement(By.name("achternaam")).sendKeys(testGebruiker.getAchternaam());
        driver.findElement(By.id("registreer")).click();
        this.testsHelper.wachtOpTitel("Registreren in Plannie");

        // Assert
        assertEquals("Registreren in Plannie",driver.getTitle());
    }

    @Test
    public void testRegistreerDubbel() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(testGebruiker.getVoornaam());
        driver.findElement(By.name("achternaam")).sendKeys(testGebruiker.getAchternaam());
        driver.findElement(By.name("email")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("wachtwoord")).sendKeys(testGebruiker.getWachtwoord());
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(testGebruiker.getWachtwoord());
        driver.findElement(By.id("registreer")).click();
        this.testsHelper.wachtOpElement("trancientWachtwoord");

        // Assert
        assertEquals("Registreren in Plannie",driver.getTitle());
    }

    @Test
    public void testLogInGeslaagd() throws InterruptedException {
        //Arrange
        this.driver.get("http://localhost:8080/index");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = testsHelper.geefTestGebruiker();

        //Activate
        driver.findElement(By.id("inloggen")).click();
        this.testsHelper.wachtOpElement("loginForm");
        driver.findElement(By.name("username")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("password")).sendKeys(testGebruiker.getWachtwoord() + Keys.RETURN);
        this.testsHelper.wachtOpTitel("Welkom bij Plannie - " + testGebruiker.getVoornaam());

        //Assert
        assertEquals("http://localhost:8080/gebruikerDetail", driver.getCurrentUrl());
    }

    @Test
    public void testLogInGefaald() throws InterruptedException {
        //Arrange
        this.driver.get("http://localhost:8080/index");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = testsHelper.geefTestGebruiker();

        //Activate
        driver.findElement(By.id("inloggen")).click();
        this.testsHelper.wachtOpElement("loginForm");
        driver.findElement(By.name("username")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("password")).sendKeys("foutwachtwoord" + Keys.RETURN);
        this.testsHelper.wachtOpTitel("Welkom bij Plannie");

        //Assert
        assertEquals("http://localhost:8080/index?error", driver.getCurrentUrl());
    }

    @Test
    public void testGebruikerWijzigenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        String gewijzigdeVoornaam = testGebruiker.getVoornaam() + "Test";
        String gewijzigdWachtwoord = testGebruiker.getWachtwoord() + "Nieuw";
        this.testsHelper.inloggen();

        // Activate
        driver.findElement(By.id("gebruikerWijzigen")).click();
        this.testsHelper.wachtOpElement("gebruikersWijzigingsFormulier");
        driver.findElement(By.name("voornaam")).clear();
        driver.findElement(By.name("voornaam")).sendKeys(gewijzigdeVoornaam);
        driver.findElement(By.id("collapseWachtwoordenKnop")).click();
        this.testsHelper.wachtOpElement("collapseWachtwoorden");
        driver.findElement(By.name("wachtwoord")).sendKeys(gewijzigdWachtwoord);
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(gewijzigdWachtwoord);
        driver.findElement(By.id("gebruikerWijzigen")).click();
        this.testsHelper.wachtOpTitel("Welkom bij Plannie - " + gewijzigdeVoornaam);

        // Assert
        assertEquals("Welkom bij Plannie - " + gewijzigdeVoornaam, driver.getTitle());
    }

    @Test
    public void testGebruikerWijzigenVoornaamFout() throws InterruptedException {
        // Arrange Voornaam Test
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        this.testsHelper.inloggen();

        // Activate Voornaam Test
        driver.findElement(By.id("gebruikerWijzigen")).click();
        this.testsHelper.wachtOpElement("gebruikersWijzigingsFormulier");
        driver.findElement(By.name("voornaam")).clear();
        driver.findElement(By.id("gebruikerWijzigen")).click();
        Thread.sleep(500);

        // Assert Voornaam Test
        assertEquals("Wijzig je gegevens - " + testGebruiker.getVoornaam(), driver.getTitle());
    }

    @Test
    public void testGebruikerWijzigenWachtwoordFout() throws InterruptedException {
        // Arrange Voornaam Test
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        String gewijzigdWachtwoord = testGebruiker.getWachtwoord() + "Nieuw";
        this.testsHelper.inloggen();

        // Activate Voornaam Test
        driver.findElement(By.id("gebruikerWijzigen")).click();
        this.testsHelper.wachtOpElement("gebruikersWijzigingsFormulier");
        driver.findElement(By.id("collapseWachtwoordenKnop")).click();
        this.testsHelper.wachtOpElement("collapseWachtwoorden");
        driver.findElement(By.name("wachtwoord")).sendKeys(gewijzigdWachtwoord);
        driver.findElement(By.id("gebruikerWijzigen")).click();
        Thread.sleep(500);

        // Assert Voornaam Test
        assertEquals("Wijzig je gegevens - " + testGebruiker.getVoornaam(), driver.getTitle());
    }
}
