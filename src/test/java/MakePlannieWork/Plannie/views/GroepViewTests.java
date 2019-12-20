package MakePlannieWork.Plannie.views;
import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GroepViewTests {

    private TestsHelper testsHelper;
    private WebDriver driver;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.testsHelper = new TestsHelper(this.driver, this.gebruikerRepository, this.passwordEncoder,
                this.groepRepository);
    }

    @After
    public void tearDown() {

        this.testsHelper.clear();
        this.driver.quit();
        this.driver = null;
        this.testsHelper = null;
    }

    @Test
    public void testGroepAanmakenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.maakTestGroep();
        this.testsHelper.registreerTestGebruikers();
        this.testsHelper.inloggen();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.testsHelper.wachtOpTitel("Hallo, " + testGebruiker.getVoornaam());

        // Activate
        driver.findElement(By.name("groepsNaam")).sendKeys(testGroep.getGroepsNaam());
        driver.findElement(By.id("groepAanmaken")).click();
        this.testsHelper.wachtOpTitel("Groepsdetails " + testGroep.getGroepsNaam());

        // Assert
        assertEquals("Groepsdetails " + testGroep.getGroepsNaam(), driver.getTitle());
    }

    @Test
    public void testGroepAanmakenFout() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.registreerTestGebruikers();
        this.testsHelper.inloggen();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        this.testsHelper.wachtOpTitel("Hallo, " + testGebruiker.getVoornaam());

        // Activate
        driver.findElement(By.name("groepsNaam")).sendKeys("");
        driver.findElement(By.id("groepAanmaken")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Hallo, " + testGebruiker.getVoornaam(), driver.getTitle());
    }

    @Test
    public void testGroepsLeden() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.maakTestGebruiker();
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testGroepNaam = testGroep.getGroepsNaam() + "Testing";

        // Activate
        driver.findElement(By.id("wijzigGroepsNaam2")).click();
        this.testsHelper.wachtOpElement("groepsNaamWijzigingsFormulier");
        driver.findElement(By.name("groepsNaam")).clear();
        driver.findElement(By.name("groepsNaam")).sendKeys(testGroepNaam);
        driver.findElement(By.id("groepsNaamWijzigen")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Groepsdetails " + testGroepNaam, driver.getTitle());
    }

    @Test
    public void testGroepNaamWijzigenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testGroepNaam = testGroep.getGroepsNaam() + "Testing";

        // Activate
        driver.findElement(By.id("wijzigGroepsNaam2")).click();
        this.testsHelper.wachtOpElement("groepsNaamWijzigingsFormulier");
        driver.findElement(By.name("groepsNaam")).clear();
        driver.findElement(By.name("groepsNaam")).sendKeys(testGroepNaam);
        driver.findElement(By.id("groepsNaamWijzigen")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Groepsdetails " + testGroepNaam, driver.getTitle());
    }

    @Test
    public void testGroepNaamWijzigenFout() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testGroepNaam = "";

        // Activate
        driver.findElement(By.id("wijzigGroepsNaam2")).click();
        this.testsHelper.wachtOpElement("groepsNaamWijzigingsFormulier");
        driver.findElement(By.name("groepsNaam")).clear();
        driver.findElement(By.name("groepsNaam")).sendKeys(testGroepNaam);
        driver.findElement(By.id("groepsNaamWijzigen")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Groepsdetails " + testGroep.getGroepsNaam(), driver.getTitle());
    }
}
