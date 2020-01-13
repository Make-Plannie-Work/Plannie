package MakePlannieWork.Plannie.views;


import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import org.junit.After;
import org.junit.Assert;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ReisItemViewTests {

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
        this.testsHelper.verwijderTestGroepenUitDatabase();
        this.driver.quit();
        this.driver = null;
        this.testsHelper = null;
    }

    @Test
    public void testNotitieAanmakenCorrect() throws InterruptedException {
        // Arrange
        this.driver.get("http://localhost:8080/gebruikerDetail");
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
//        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        String testNotitieNaam = "TestNotitie";
        String testNotitieStartDatum = "2020-01-01";
        String testNotitieTekst = "Test tekst notitie";
        boolean testNotitieToegevoegd = false;

        // Activate
        Thread.sleep(5000);
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        Thread.sleep(5000);
        this.driver.findElement(By.id("notitieKeuze")).click();
        Thread.sleep(500);
        this.driver.findElement(By.id("notitieTitel")).sendKeys(testNotitieNaam);
        this.driver.findElement(By.id("notitieDatum")).sendKeys(testNotitieStartDatum);
        this.driver.findElement(By.id("notitieTekst")).sendKeys(testNotitieTekst);
        this.driver.findElement(By.id("notitieAanmaken")).click();
        Thread.sleep(500);

        // Assert
        if (this.driver.findElement(By.id("NotitieDetails" + testNotitieNaam)).getSize().width != 0) {
            testNotitieToegevoegd = true;
        }
        assertTrue(testNotitieToegevoegd);


    }
}
