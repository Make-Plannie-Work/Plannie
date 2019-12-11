package MakePlannieWork.Plannie.views;

import org.junit.After;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GebruikerNieuwTest {

    // TODO De @Before en @After moeten vervangen worden door de '@...Each' varianten, maar dit levert nu nog een null point error op.
    private WebDriver driver;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");

        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {

        driver.quit();
        driver = null;
    }

    @Test
    public void testRegistreren() throws InterruptedException {
        // Arrange
        driver.get("http://localhost:8080/registreren");

        // Activate
        driver.findElement(By.name("voornaam")).sendKeys("Daniel");
        driver.findElement(By.name("achternaam")).sendKeys("Kuperus");
        driver.findElement(By.name("email")).sendKeys("test.testing@daniel.com");
        driver.findElement(By.name("wachtwoord")).sendKeys("echtgeen123");
        driver.findElement(By.name("trancientWachtwoord")).sendKeys("echtgeen123");

        // Assert
        Thread.sleep(5000);
    }
}
