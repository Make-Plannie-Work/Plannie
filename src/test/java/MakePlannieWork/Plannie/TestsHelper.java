package MakePlannieWork.Plannie;

import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.PollOptie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * TestsHelper kan worden aangeroepen door alle tests, om herhaling van code te voorkomen.
 * Hij kan testgebruikers aanmaken, registeren, inloggen, verwijderen en teruggeven.
 *
 * In de toekomst zal het behalve gebruikers, ook de groepen en reizen kunnen aanmaken om mee te testen.
 */

public class TestsHelper {

    // Standaard Test Identifiers
    private static final String URL_GEBRUIKER_DETAIL = "http://localhost:8080/gebruikerDetail";
    private static final String URL_GROEP_DETAIL = "http://localhost:8080/groepDetail/";
    private static final String URL_INDEX = "http://localhost:8080/index";
    private static final String GROEP_NAAM_TEXTFIELD = "groepsNaam";

    // Gebruikers Static Test Waarden
    private static final String GEBRUIKER_VOORNAAM = "testVoornaam";
    private static final String GEBRUIKER_ACHTERNAAM = "testAchternaam";
    private static final String GEBRUIKER_EMAIL = ".testing@test.com";
    private static final String GEBRUIKER_WACHTWOORD = "testWachtwoord";

    // Groepen Static Test Waarden
    private static final String GROEP_NAAM = "testGroep";

    // ReisItems Static Test Waarden
    private static final String REIS_NAAM = "testReis";
    private static final String REIS_DATUM = "2020-06-10";
    // Notitie items Static Test Waarden
    private static final String NOTITIE_NAAM = "testNotitie";
    private static final String NOTITIE_TEKST = "Test tekst, die heel lang is.";
    private static final String NOTITIE_STARTDATUM = REIS_DATUM;
    // Poll items Static Test Waarden
    private static final String POLL_NAAM = "testPoll";
    private static final String POLL_STARTDATUM = REIS_DATUM;
    private static final String[] POLL_OPTIES = {"stem optie 1","stem optie 2","stem optie 3"};

    private ArrayList<Gebruiker> testGebruikers = new ArrayList<>();
    private ArrayList<Groep> testGroepen = new ArrayList<>();
    private ArrayList<ReisItem> testReizen = new ArrayList<>();
    private ArrayList<Notitie> testNotities = new ArrayList<>();
    private ArrayList<Poll> testPolls = new ArrayList<>();


    private GebruikerRepository gebruikerRepository;
    private ReisItemRepository reisItemRepository;
    private GroepRepository groepRepository;
    private PasswordEncoder passwordEncoder;
    private WebDriver driver;

    private WebDriverWait wacht;

    // Constructors, voor alle mogelijke opties.
    public TestsHelper(WebDriver driver, GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder,
                       GroepRepository groepRepository, ReisItemRepository reisItemRepository) {
        this.driver = driver;
        this.gebruikerRepository = gebruikerRepository;
        this.reisItemRepository = reisItemRepository;
        this.groepRepository = groepRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TestsHelper(WebDriver driver, GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder,
                       GroepRepository groepRepository) {
        this(driver, gebruikerRepository, passwordEncoder, groepRepository, null);
    }

    public TestsHelper(WebDriver driver, GebruikerRepository gebruikerRepository, PasswordEncoder passwordEncoder) {
        this(driver, gebruikerRepository, passwordEncoder, null);
    }

    public TestsHelper(WebDriver driver) {
        this(driver, null, null);
    }

    // Multi-actie methodes, om code herhaling te voorkomen.
    public void clear() {
        verwijderTestGebruikersUitDatabase();
        verwijderTestGroepenUitDatabase();
    }

    public void zetTestGebruikerEnTestGroepKlaar() {
        maakTestGebruiker();
        maakTestGroep();
        registreerTestGebruikers();
        inloggen();
        registreerTestGroepen();
    }

    public void zetTestGebruikerEnGroepEnReisKlaar() {
        zetTestGebruikerEnTestGroepKlaar();
        this.driver.get(URL_GROEP_DETAIL + geefTestGroep().getGroepId());
        maakTestReis();
        registreerTestReizen();
    }

    // Wachten tot de volgende pagina geladen is:
    public void wachtOpElement(String idElement) {
        if (this.wacht == null) {
            this.wacht = new WebDriverWait(this.driver, 10);
        }
        this.wacht.until(ExpectedConditions.visibilityOfElementLocated(By.id(idElement)));
    }

    public void wachtOpTitel(String titel) {
        if (this.wacht == null) {
            this.wacht = new WebDriverWait(this.driver, 10);
        }
        this.wacht.until(ExpectedConditions.titleIs(titel));
    }

    // Test Groepen aanmaken:
    public void maakTestGroepen(int aantal) {
        this.testGroepen.clear();
        for (int i = 0; i < aantal; i++) {
            Groep testGroep = new Groep();
            testGroep.setGroepsNaam(GROEP_NAAM + i);
            this.testGroepen.add(testGroep);
        }
    }

    public void maakTestGroep() {
        maakTestGroepen(1);
    }

    // Test Groepen geven: Je kan alle groepen, of 1 opvragen.
    public ArrayList<Groep> geefTestGroepen() {
        return this.testGroepen;
    }

    public Groep geefTestGroep() {
        return geefTestGroep(0);
    }

    public Groep geefTestGroep(int index) {
        return this.testGroepen.get(index);
    }

    // Test Groepen registeren
    public void registreerTestGroepen() {
        int index = 0;
        for (Groep testGroep : testGroepen) {
            registreerTestGroep(index);
            index++;
        }
    }

    public void registreerTestGroep(int index) {
        Groep testGroep = this.testGroepen.get(index);
        String beginUrl = driver.getCurrentUrl();
        this.driver.get(URL_GEBRUIKER_DETAIL);
        String inlogNaam = this.driver.getTitle();
        inlogNaam = inlogNaam.replace("Welkom bij Plannie - ", "");
        driver.findElement(By.name(GROEP_NAAM_TEXTFIELD)).sendKeys(testGroep.getGroepsNaam() + Keys.RETURN);

        // Opzoeken welke testgebruiker ingelogd is:
        Gebruiker testGebruiker = null;
        int iteratie = 0;
        while (testGebruiker == null && iteratie < testGebruikers.size()) {
            if (testGebruikers.get(iteratie).getVoornaam().equals(inlogNaam)) {
                testGebruiker = testGebruikers.get(iteratie);

                // Als deze testgebruiker nog niet opgehaald was uit de database, wordt dat nu gedaan.
                if (testGebruiker.getGebruikersId() == null) {
                    testGebruiker = gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail());
                }

                // De zojuist aangemaakte groep wordt in de database gevonden, zodat de GroepsID bekend is.
                List<Groep> aangemaakteGroep = groepRepository.findByGroepsleden_GebruikersId(testGebruiker.getGebruikersId());
                if (!aangemaakteGroep.isEmpty()) {
                    testGroep = aangemaakteGroep.get(aangemaakteGroep.size() - 1);
                    testGroepen.set(index, testGroep);
                }
            }
            iteratie ++;
        }

        this.driver.get(beginUrl);
    }

    // Test Groepen verwijderen uit database
    public void verwijderTestGroepenUitDatabase() {
        int index = 0;
        for (Groep testGroep : testGroepen) {
            verwijderTestGroepUitDatabase(index);
            index++;
        }
    }

    public void verwijderTestGroepUitDatabase(int index) {
        Groep testGroep = this.testGroepen.get(index);
        List<Groep> testGroepenInDatabase = groepRepository.findByGroepsleden_GebruikersId(testGroep.getAanmaker());
        if (!testGroepenInDatabase.isEmpty()) {
            groepRepository.deleteAll(testGroepenInDatabase);
        }
    }

    // Test Reizen aanmaken:
    public void maakTestReizen(int aantal) {
        this.testGroepen.clear();
        for (int i = 0; i < aantal; i++) {
            ReisItem testReis = new ReisItem();
            testReis.setNaam(REIS_NAAM + i);
            this.testReizen.add(testReis);
        }
    }

    public void maakTestReis() {
        maakTestReizen(1);
    }

    // Test Reizen registreren
    public void registreerTestReizen() {
        int index = 0;
        for (ReisItem testReis : testReizen) {
            registreerTestReis(index);
            index++;
        }
    }

    public void registreerTestReis(int reisIndex) {
        // Alleen te gebruiken vanuit een groepDetail pagina.
        String beginUrl = driver.getCurrentUrl();
        driver.findElement(By.id("reisNaam")).sendKeys(testReizen.get(reisIndex).getNaam() + Keys.ENTER);
        driver.get(beginUrl);
    }

    // Test Reizen geven: Je kan alle reizen, of 1 opvragen.
    public ArrayList<ReisItem> geefTestReizen() {
        return this.testReizen;
    }

    public ReisItem geefTestReis() {
        return geefTestReis(0);
    }

    public ReisItem geefTestReis(int index) {
        return this.testReizen.get(index);
    }

    // Test Reizen verwijderen uit database
    public void verwijderTestReizenUitDatabase() {
        int index = 0;
        for (ReisItem testreis : testReizen) {
            verwijderTestReisUitDatabase(index);
            index++;
        }
    }

    public void verwijderTestReisUitDatabase(int index) {
        Optional<ReisItem> testReis = reisItemRepository.findById(testReizen.get(index).getReisItemId());
        if (testReis.isPresent()) {
            reisItemRepository.deleteById(testReis.get().getReisItemId());
        }
    }

    // Test Notities aanmaken:
    public void maakTestNotities(int aantal) {
        for (int i = 0; i < aantal; i++) {
            Notitie testNotitie = new Notitie();
            testNotitie.setNaam(NOTITIE_NAAM + i);
            testNotitie.setStartDatum(NOTITIE_STARTDATUM);
            testNotitie.setTekst(NOTITIE_TEKST + i);
            this.testNotities.add(testNotitie);
        }
    }

    public void maakTestNotitie() {
        maakTestNotities(1);
    }

    // Test Notities geven: Je kan alle notities, of 1 opvragen.
    public ArrayList<Notitie> geefTestNotities() {
        return this.testNotities;
    }

    public Notitie geefTestNotitie() {
        return geefTestNotitie(0);
    }

    public Notitie geefTestNotitie(int index) {
        return this.testNotities.get(0);
    }

    // Test Polls aanmaken:
    public void maakTestPolls(int aantal) {
        for (int i = 0; i < aantal; i++) {
            Poll testPoll = new Poll();
            testPoll.setNaam(POLL_NAAM + i);
            testPoll.setStartDatum(POLL_STARTDATUM);

            for (String optie : POLL_OPTIES) {
                PollOptie pollOptie = new PollOptie();
                pollOptie.setStemOptie(optie + i);
                testPoll.voegPollOptieToe(pollOptie);
            }

            this.testPolls.add(testPoll);
        }
    }

    public void maakTestPoll() {
        maakTestPolls(1);
    }

    // Test Polls geven: Je kan alle polls, of 1 opvragen.
    public ArrayList<Poll> geefTestPolls() {
        return this.testPolls;
    }

    public Poll geefTestPoll() {
        return geefTestPoll(0);
    }

    public Poll geefTestPoll(int index) {
        return this.testPolls.get(0);
    }

    // Test Gebruikers aanmaken:
    public void maakTestGebruikers(int aantal) {
        for (int i = 0; i < aantal; i++) {
            Gebruiker testGebruiker = new Gebruiker();
            testGebruiker.setVoornaam(GEBRUIKER_VOORNAAM + i);
            testGebruiker.setAchternaam(GEBRUIKER_ACHTERNAAM + i);
            testGebruiker.setEmail(GEBRUIKER_VOORNAAM + i + GEBRUIKER_EMAIL);
            testGebruiker.setWachtwoord(GEBRUIKER_WACHTWOORD + i);
            this.testGebruikers.add(testGebruiker);
        }
    }

    public void maakTestGebruiker() {
        maakTestGebruikers(1);
    }

    // Test Gebruiker inloggen:
    public void inloggen() {
        inloggen(0);
    }

    public void inloggen(int index) {
        Gebruiker testGebruiker = testGebruikers.get(index);
        inloggen(testGebruiker);
    }

    public void inloggen(Gebruiker testGebruiker) {
        String beginUrl = driver.getCurrentUrl();
        this.driver.get(URL_INDEX);
        driver.findElement(By.id("inloggen")).click();
        wachtOpElement("loginForm");
        driver.findElement(By.name("username")).sendKeys(testGebruiker.getEmail());
        driver.findElement(By.name("password")).sendKeys(testGebruiker.getWachtwoord() + Keys.RETURN);
        this.driver.get(beginUrl);
    }

    // Test Gebruikers geven: Je kan alle gebruikers, of 1 opvragen.
    public ArrayList<Gebruiker> geefTestGebruikers() {
        return this.testGebruikers;
    }

    public Gebruiker geefTestGebruiker() {
        return geefTestGebruiker(0);
    }

    public Gebruiker geefTestGebruiker(int index) {
        return this.testGebruikers.get(index);
    }

    // Test Gebruikers registeren
    public void registreerTestGebruikers() {
        int index = 0;
        for (Gebruiker testGebruiker : testGebruikers) {
            registreerTestGebruiker(index);
            index++;
        }
    }

    public void registreerTestGebruiker(int index) {
        Gebruiker testGebruiker = this.testGebruikers.get(index);
        String wachtwoord = testGebruiker.getWachtwoord();
        String encodedWachtwoord = passwordEncoder.encode(wachtwoord);

        testGebruiker.setWachtwoord(encodedWachtwoord);
        testGebruiker.setTrancientWachtwoord(encodedWachtwoord);
        gebruikerRepository.save(testGebruiker);

        // Gebruikers ID ophalen en wachtwoord normaliseren.
        testGebruiker = gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail());
        testGebruiker.setWachtwoord(wachtwoord);
        testGebruikers.set(index, testGebruiker);
    }

    // Test Gebruikers verwijderen uit database
    public void verwijderTestGebruikersUitDatabase() {
        int index = 0;
        for (Gebruiker testGebruiker : testGebruikers) {
            verwijderTestGebruikerUitDatabase(index);
            index++;
        }
    }

    public void verwijderTestGebruikerUitDatabase(int index) {
        Gebruiker testGebruiker = this.testGebruikers.get(index);
        List<Gebruiker> testGebruikersInDatabase = gebruikerRepository.findGebruikersByEmail(testGebruiker.getEmail());
        if (!testGebruikersInDatabase.isEmpty()) {
            gebruikerRepository.deleteAll(testGebruikersInDatabase);
        }
    }
}
