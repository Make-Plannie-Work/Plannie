package MakePlannieWork.Plannie.views;


import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.PollOptie;
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

import java.util.ArrayList;

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

        this.testsHelper.verwijderTestGroepenUitDatabase();
        this.testsHelper.verwijderTestGebruikersUitDatabase();
        this.driver.quit();
        this.driver = null;
        this.testsHelper = null;
    }

    @Test
    public void testNotitieAanmakenCorrect() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        this.testsHelper.maakTestNotitie();
        Notitie testNotitie = this.testsHelper.geefTestNotitie();
        boolean testNotitieToegevoegd = false;


        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        this.driver.findElement(By.id("notitieKeuze")).click();
        this.testsHelper.wachtOpTitel("Notitie aanmaken - " + testGebruiker.getVoornaam());
        this.driver.findElement(By.id("notitieTitel")).sendKeys(testNotitie.getNaam());
        this.driver.findElement(By.id("notitieDatum")).sendKeys(testNotitie.getStartDatum());
        this.driver.findElement(By.id("notitieTekst")).sendKeys(testNotitie.getTekst());
        this.driver.findElement(By.id("notitieAanmaken")).click();
        this.testsHelper.wachtOpTitel("Plannie - ReisDetails " + testReisItem.getNaam());

        // Assert
        if (this.driver.findElement(By.id("NotitieDetails" + testNotitie.getNaam())).getSize().width != 0) {
            testNotitieToegevoegd = true;
        }
        assertTrue(testNotitieToegevoegd);
    }

    @Test
    public void testNotitieAanmakenFout() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        String testNotitieTitel = "";
        String testNotitieDatum = "";
        String testNotitieTekst = "";

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        this.driver.findElement(By.id("notitieKeuze")).click();
        this.testsHelper.wachtOpTitel("Notitie aanmaken - " + testGebruiker.getVoornaam());
        this.driver.findElement(By.id("notitieTitel")).sendKeys(testNotitieTitel);
        this.driver.findElement(By.id("notitieDatum")).sendKeys(testNotitieDatum);
        this.driver.findElement(By.id("notitieTekst")).sendKeys(testNotitieTekst);
        this.driver.findElement(By.id("notitieAanmaken")).click();

        // Assert
        assertEquals("Notitie aanmaken - " + testGebruiker.getVoornaam(), driver.getTitle());
    }


    @Test
    public void testPollAanmakenCorrect() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReis = this.testsHelper.geefTestReis();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        this.testsHelper.maakTestPoll();
        Poll testPoll = this.testsHelper.geefTestPoll();
        ArrayList<String> pollOpties = this.testsHelper.geefTestPollStemOpties();
        boolean testPollToegevoegd = false;

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReis.getReisItemId());
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        this.driver.findElement(By.id("pollKeuze")).click();
        this.testsHelper.wachtOpTitel("Poll aanmaken - " + testGebruiker.getVoornaam());
        this.driver.findElement(By.id("pollTitel")).sendKeys(testPoll.getNaam());
        this.driver.findElement(By.id("pollDatum")).sendKeys(testPoll.getStartDatum());
        for (String optie : pollOpties) {
            this.driver.findElement(By.id("pollOpties")).sendKeys(optie + ", ");
        }
        this.driver.findElement(By.id("pollAanmaken")).click();
        this.testsHelper.wachtOpTitel("Plannie - ReisDetails " + testReis.getNaam());

        // Assert
        if (this.driver.findElement(By.id("PollDetails" + testPoll.getNaam())).getSize().width != 0) {
            testPollToegevoegd = true;
        }
        assertTrue(testPollToegevoegd);
    }

    @Test
    public void testPollAanmakenFout() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReis = this.testsHelper.geefTestReis();
        this.testsHelper.maakTestPoll();
        Poll testPoll = this.testsHelper.geefTestPoll();


        // Activate
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        this.driver.findElement(By.id("pollKeuze")).click();
        this.testsHelper.wachtOpTitel("Poll aanmaken");




        // Assert
    }
}
