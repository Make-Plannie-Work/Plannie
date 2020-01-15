package MakePlannieWork.Plannie.views;


import MakePlannieWork.Plannie.TestsHelper;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
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

import javax.persistence.criteria.CriteriaBuilder;
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

        //this.testsHelper.verwijderTestGroepenUitDatabase();
        //this.testsHelper.verwijderTestGebruikersUitDatabase();
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

    // In deze test wordt bij het aanmaken van een notitie het tekstveld leeggelaten, dit is de fout die getest wordt.
    @Test
    public void testNotitieAanmakenFout() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisKlaar();
        Gebruiker testGebruiker = this.testsHelper.geefTestGebruiker();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        this.testsHelper.maakTestNotitie();
        Notitie testNotitie = this.testsHelper.geefTestNotitie();

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("keuzeReisItemMenu")).click();
        this.driver.findElement(By.id("notitieKeuze")).click();
        this.testsHelper.wachtOpTitel("Notitie aanmaken - " + testGebruiker.getVoornaam());
        this.driver.findElement(By.id("notitieTitel")).sendKeys(testNotitie.getNaam());
        this.driver.findElement(By.id("notitieDatum")).sendKeys(testNotitie.getStartDatum());
        this.driver.findElement(By.id("notitieTekst")).clear();
        this.driver.findElement(By.id("notitieAanmaken")).click();

        // Assert
        assertEquals("Notitie aanmaken - " + testGebruiker.getVoornaam(), driver.getTitle());
    }

    @Test
    public void testNotitieWijzigenCorrect() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisEnNotitieKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        Notitie testNotitie = this.testsHelper.geefTestNotitie();
        String gewijzigdeNotitieNaam = "Testen";
        String gewijzigdeNotitieDatum = "2020-01-01";
        String gewijzigdeNotitieTekst = "Gewijzigde tekst";

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("NotitieDetails" + testNotitie.getNaam())).click();
        this.driver.findElement(By.id("naam")).clear();
        this.driver.findElement(By.id("startDatum")).clear();
        this.driver.findElement(By.id("tekst")).clear();
        this.driver.findElement(By.id("naam")).sendKeys(gewijzigdeNotitieNaam);
        this.driver.findElement(By.id("startDatum")).sendKeys(gewijzigdeNotitieDatum);
        this.driver.findElement(By.id("tekst")).sendKeys(gewijzigdeNotitieTekst);
        this.driver.findElement(By.id("notitieWijzigen")).click();

        // Assert
        assertEquals("Testen", this.driver.findElement(By.id("NotitieDetails" + gewijzigdeNotitieNaam)).getText());
    }

    // In deze test wordt bij het wijzigen van een notitie het naamveld leeggemaakt, dit is de fout die getest wordt.
    @Test
    public void testNotitieWijzigenfout() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisEnNotitieKlaar();
        Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        Notitie testNotitie = this.testsHelper.geefTestNotitie();

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("NotitieDetails" + testNotitie.getNaam())).click();
        this.driver.findElement(By.id("naam")).clear();
        this.driver.findElement(By.id("notitieWijzigen")).click();

        // Assert
        assertEquals("http://localhost:8080/" + testGroep.getGroepId() + "/" + testReisItem.getReisItemId()
                + "/" + testNotitie.getReisItemId() + "/NotitieWijzigen", this.driver.getCurrentUrl());
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
        this.driver.findElement(By.id("pollAanmaken")).click();
        Thread.sleep(500);

        // Assert
        assertEquals("Poll aanmaken - " + testGebruiker.getVoornaam(), driver.getTitle());
    }

    // Test stemmen op poll goed
    @Test
    public void testStemmenOpPollCorrect() throws InterruptedException {
        // Arrange
        this.testsHelper.zetTestGebruikerEnGroepEnReisEnPollKlaar();
         Groep testGroep = this.testsHelper.geefTestGroep();
        ReisItem testReisItem = this.testsHelper.geefTestReis();
        Poll testPoll = this.testsHelper.geefTestPoll();

        // Activate
        this.driver.get("http://localhost:8080/" + testGroep.getGroepId() + "/reisItemDetail/" + testReisItem.getReisItemId());
        this.driver.findElement(By.id("PollDetails" + testPoll.getNaam())).click();
        Thread.sleep(5000);
        this.driver.findElement(By.id("stemmenOp" + "stem optie 1")).click();

        // Assert
        assertEquals("Jij hebt hierop gestemd.", this.driver.findElement(By.id("gestemdOp" + "stem optie 1")).getText());
    }
}
