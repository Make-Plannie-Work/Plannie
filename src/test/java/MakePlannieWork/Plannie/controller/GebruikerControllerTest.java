package MakePlannieWork.Plannie.controller;

import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GebruikerControllerTest {

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {

    }

    @Test
    public void testRegistreren() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:8080/registreren");

        Thread.sleep(1000);
        driver.findElement(By.name("voornaam")).sendKeys("Daniel");
        driver.findElement(By.name("achternaam")).sendKeys("Kuperus");
        driver.findElement(By.name("email")).sendKeys("test.testing@daniel.com");
        driver.findElement(By.name("wachtwoord")).sendKeys("echtgeen123");
        driver.findElement(By.name("trancientWachtwoord")).sendKeys("echtgeen123");
        Thread.sleep(5000);

        driver.quit();
    }
}