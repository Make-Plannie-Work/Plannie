package MakePlannieWork.Plannie.views;
import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
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

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ReisViewTests {

    private TestsHelper testsHelper;
    private WebDriver driver;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private ReisItemRepository reisItemRepository;

    @Autowired
    private GroepRepository groepRepository;

    @Before
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "Algemeen/chromedriver.exe");
        this.driver = new ChromeDriver();
        this.testsHelper = new TestsHelper(this.driver, this.gebruikerRepository, this.passwordEncoder,
                this.groepRepository, this.reisItemRepository);
    }

    @After
    public void tearDown() {

        this.testsHelper.verwijderTestGebruikersUitDatabase();
        this.driver.quit();
        this.driver = null;
        this.testsHelper = null;
    }

    @Test
    public void testReisAanmakenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testReisNaam = "TestReis";

        // Activate
        this.driver.findElement(By.id("reisNaam")).sendKeys(testReisNaam);
        Thread.sleep(500);
        this.driver.findElement(By.id("reisItemAanmaken")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Plannie - ReisDetails " + testReisNaam, driver.getTitle());
    }

    @Test
    public void testReisAanmakenFout() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testReisNaam = "";

        // Activate

        Thread.sleep(500);
        this.driver.findElement(By.id("reisNaam")).sendKeys(testReisNaam);
        this.driver.findElement(By.id("reisItemAanmaken")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Plannie - Groepsdetails " + testGroep.getGroepsNaam(), driver.getTitle());
    }

    @Test
    public void testReisNaamWijzigenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnTestGroepKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        this.driver.get("http://localhost:8080/groepDetail/" + testGroep.getGroepId());
        String testReisNaam = "TestReis";

        // Activate
        Thread.sleep(500);
        this.driver.findElement(By.id("reisNaam")).sendKeys(testReisNaam);
        this.driver.findElement(By.id("reisItemAanmaken")).click();
        Thread.sleep(500);
        driver.findElement(By.id("wijzigReisItem")).click();
        this.testsHelper.wachtOpElement("reisItemWijzigingsFormulier");
        Thread.sleep(500);
        driver.findElement(By.name("naam")).clear();
        driver.findElement(By.name("naam")).sendKeys(testReisNaam + "2");
        driver.findElement(By.id("reisItemWijzigen")).click();
        Thread.sleep(500);
        this.testsHelper.wachtOpTitel("Plannie - ReisDetails " + testReisNaam + "2");

        // Assert
        assertEquals("Plannie - ReisDetails " + testReisNaam + "2", driver.getTitle());
    }
}
