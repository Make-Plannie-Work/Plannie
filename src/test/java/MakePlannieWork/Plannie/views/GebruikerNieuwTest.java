package MakePlannieWork.Plannie.views;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GebruikerNieuwTest {

    private static final String VOORNAAM = "testVoornaam";
    private static final String ACHTERNAAM = "testAchternaam";
    private static final String EMAIL = "test.testing@test.com";
    private static final String WACHTWOORD = "testWachtwoord";
    private WebDriver driver;

    @Autowired
    GebruikerRepository gebruikerRepository;

    @Before
    public void setUp() {
        // Oude test gebruiker verwijderen.
        Gebruiker testGebruiker = gebruikerRepository.findGebruikerByEmail(EMAIL);
        if (testGebruiker != null) {
            gebruikerRepository.delete(testGebruiker);
        }

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown() {

        this.driver.quit();
        this.driver = null;
    }

    @Test
    public void testRegistreerCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(VOORNAAM);
        driver.findElement(By.name("achternaam")).sendKeys(ACHTERNAAM);
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("wachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.id("registreer")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Home Plannie",driver.getTitle());
    }

    @Test
    public void testRegistreerFout() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(VOORNAAM);
        driver.findElement(By.name("achternaam")).sendKeys(ACHTERNAAM);
        driver.findElement(By.id("registreer")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Registreren in Plannie",driver.getTitle());
    }

    @Test
    public void testRegistreerDubbel() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(VOORNAAM);
        driver.findElement(By.name("achternaam")).sendKeys(ACHTERNAAM);
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("wachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.id("registreer")).click();
        Thread.sleep(500);
        this.driver.get("http://localhost:8080/registreren");
        driver.findElement(By.name("voornaam")).sendKeys(VOORNAAM);
        driver.findElement(By.name("achternaam")).sendKeys(ACHTERNAAM);
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("wachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.id("registreer")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Registreren in Plannie",driver.getTitle());
    }
}
