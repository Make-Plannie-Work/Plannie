package MakePlannieWork.Plannie.views;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GebruikerNieuwTest {

    private static final String VOORNAAM = "testVoornaam";
    private static final String ACHTERNAAM = "testAchternaam";
    private static final String EMAIL = "test.testing@test.com";
    private static final String WACHTWOORD = "testWachtwoord";
    private WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
    }

    @After
    public void tearDown() {

        this.driver.quit();
        this.driver = null;
    }

    @Test
    public void testRegistreren() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/registreren");

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys(VOORNAAM);
        driver.findElement(By.name("achternaam")).sendKeys(ACHTERNAAM);
        driver.findElement(By.name("email")).sendKeys(EMAIL);
        driver.findElement(By.name("wachtwoord")).sendKeys(WACHTWOORD);
        driver.findElement(By.name("trancientWachtwoord")).sendKeys(WACHTWOORD);

        // Assert
        assertEquals(VOORNAAM, driver.findElement(By.name("voornaam")).getAttribute("value"));
        assertEquals(ACHTERNAAM, driver.findElement(By.name("achternaam")).getAttribute("value"));
        assertEquals(EMAIL, driver.findElement(By.name("email")).getAttribute("value"));
        assertEquals(WACHTWOORD, driver.findElement(By.name("wachtwoord")).getAttribute("value"));
        assertEquals(WACHTWOORD, driver.findElement(By.name("trancientWachtwoord")).getAttribute("value"));
    }
}
