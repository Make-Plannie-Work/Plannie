package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.RolRepository;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.naming.Binding;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.UUID;

@Controller
public class TestDataController {

    @Autowired
    RolRepository rolRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    GebruikerRepository gebruikerRepository;
    @Autowired
    GroepRepository groepRepository;

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
    private static final String POLL_OPTIES_COMPLEET = "stem optie 1,stem optie 2,stem optie 3";

    private ArrayList<Gebruiker> testGebruikers = new ArrayList<>();
    private ArrayList<Groep> testGroepen = new ArrayList<>();
    private ArrayList<ReisItem> testReizen = new ArrayList<>();
    private ArrayList<Notitie> testNotities = new ArrayList<>();
    private ArrayList<Poll> testPolls = new ArrayList<>();

    @GetMapping("/testdata")
    public String testDataInladen(Model model) {
        String notificatie = "// TEST DATA AANMAKEN //";
        System.out.println(notificatie);

        // Gebruikers opslaan
        int aantal = 3;
        System.out.println("// Gebruikers aanmaken: ");
        for (int i = 0; i < aantal; i++) {
            Gebruiker testGebruiker = new Gebruiker();
            testGebruiker.setVoornaam(GEBRUIKER_VOORNAAM + i);
            testGebruiker.setAchternaam(GEBRUIKER_ACHTERNAAM + i);
            testGebruiker.setEmail(GEBRUIKER_VOORNAAM + i + GEBRUIKER_EMAIL);
            testGebruiker.setIdentifier(UUID.randomUUID().toString());
            testGebruiker.setRollen(Arrays.asList(rolRepository.findRolByRolNaam("ROLE_USER")));
            testGebruiker.setWachtwoord(passwordEncoder.encode(GEBRUIKER_WACHTWOORD + i));
            if (gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()) == null) {
                this.testGebruikers.add(gebruikerRepository.save(testGebruiker));
                notificatie = "Gebruiker toegevoegd: " + testGebruiker.getEmail();
            } else {
                this.testGebruikers.add(gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()));
                notificatie = "Gebruiker bestond al: " + testGebruiker.getEmail();
            }

            System.out.println(notificatie);
        }

        // Groepen opslaan
        Gebruiker groepBeheerder = testGebruikers.get(0);
        aantal = 2;
        System.out.println("// Groepen aanmaken: ");
        for (int i = 0; i < aantal; i++) {
            Groep groep = new Groep();
            groep.setGroepsNaam(GROEP_NAAM + i);
            groep.getGroepsleden().add(groepBeheerder);
            groep.setAanmaker(groepBeheerder.getGebruikersId());
            if (groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()) == null) {
                this.testGroepen.add(groepRepository.save(groep));
                notificatie = "Groep toegevoegd: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail();
            } else {
                this.testGroepen.add(groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()));
                notificatie = "Groep bestond al: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail();
            }

            System.out.println(notificatie);
        }

        // Groepsleden toevoegen
        Groep toevoegenGroep = testGroepen.get(0);
        ArrayList<Gebruiker> toevoegenLeden = new ArrayList<>();
        toevoegenLeden.add(testGebruikers.get(1));
        toevoegenLeden.add(testGebruikers.get(2));
        System.out.println("// Groepsleden toevoegen: ");
        for (int i = 0; i < toevoegenLeden.size(); i++) {
            if (!toevoegenGroep.getGroepsleden().contains(toevoegenLeden.get(i))) {
                toevoegenGroep.getGroepsleden().add(toevoegenLeden.get(i));
                groepRepository.save(toevoegenGroep);
                toevoegenGroep = groepRepository.findByAanmakerAndGroepsNaam(toevoegenGroep.getAanmaker(),toevoegenGroep.getGroepsNaam());
                int index = testGroepen.indexOf(toevoegenGroep);
                this.testGroepen.set(index,toevoegenGroep);
                notificatie = "Groepslid toegevoegd: " + toevoegenLeden.get(i).getEmail() + ". Groep: " + toevoegenGroep.getGroepsNaam();
            } else {
                notificatie = "Groepslid bestond al: " + toevoegenLeden.get(i).getEmail() + ". Groep: " + toevoegenGroep.getGroepsNaam();
            }

            System.out.println(notificatie);
        }


        return "redirect:/index";
    }


}
