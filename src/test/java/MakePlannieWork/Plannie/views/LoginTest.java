package MakePlannieWork.Plannie.views;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LoginTest {

    // TODO Hoe krijgen we de Admin logingegevens vanuit een getter?
    private static final String ADMIN_EMAIL = "Plannie@planet.nl";
    private static final String ADMIN_PASS = "PlannieAdmin";
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
    public void testLogInGeslaagd() throws InterruptedException {
        //Arrange
        this.driver.get("http://localhost:8080/index");

        //Activate
        driver.findElement(By.name("username")).sendKeys(ADMIN_EMAIL);
        driver.findElement(By.name("password")).sendKeys(ADMIN_PASS + Keys.RETURN);
        // TODO Inplaats van sleep, zouden we eigenlijk een wait.until moeten gebruiken.
        Thread.sleep(500);

        //Assert
        assertEquals("http://localhost:8080/gebruikerDetail", driver.getCurrentUrl());
    }

    @Test
    public void testLogInGefaald() throws InterruptedException {
        //Arrange
        this.driver.get("http://localhost:8080/index");

        //Activate
        driver.findElement(By.name("username")).sendKeys(ADMIN_EMAIL);
        driver.findElement(By.name("password")).sendKeys("foutwachtwoord" + Keys.RETURN);
        Thread.sleep(500);

        //Assert
        assertEquals("http://localhost:8080/index?error", driver.getCurrentUrl());
    }
}


