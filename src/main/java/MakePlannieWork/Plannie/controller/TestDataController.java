package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
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
import java.text.DateFormat;
import java.util.*;

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
    @Autowired
    ReisItemRepository reisItemRepository;

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
    private static final String[] POLL_OPTIES = {"stem optie 1", "stem optie 2", "stem optie 3"};
    private static final String POLL_OPTIES_COMPLEET = "stem optie 1,stem optie 2,stem optie 3";

    private ArrayList<Gebruiker> testGebruikers = new ArrayList<>();
    private ArrayList<Groep> testGroepen = new ArrayList<>();
    private ArrayList<ReisItem> testReizen = new ArrayList<>();
    private ArrayList<Notitie> testNotities = new ArrayList<>();
    private ArrayList<Poll> testPolls = new ArrayList<>();

    @GetMapping("/testdata")
    public String testDataInladen(Model model) {
        String notificatie = "--// TEST DATA AANMAKEN //--";
        System.out.println(notificatie);

        // Gebruikers opslaan
        String[] csvGebruiker = {"Voornaam,Achternaam,EMail,Wachtwoord,Rol",
                "Daniel,Kuperus,daniel.kuperus@gmail.com,123,ROLE_USER",
                "Tabitha,Krist,tabitha.krist@gmail.com,123,ROLE_USER",
                "Wouter,Meindertsma,wouter.meindertsma@gmail.com,123,ROLE_USER"};
        gebruikersAanmaken(csvGebruiker);

        // Groepen opslaan
        String[] csvGroep = {"GroepNaam,GroepBeheerder,GroepsLid1,GroepsLid2,GroepsLid3",
                "UsBikers,daniel.kuperus@gmail.com",
                "MakeITWork,daniel.kuperus@gmail.com,tabitha.krist@gmail.com,wouter.meindertsma@gmail.com"};
        groepenAanmaken(csvGroep);

        // Reizen opslaan
        String[] csvReis = {"GroepBeheerderEmail,GroepNaam,ReisNaam",
                "daniel.kuperus@gmail.com,UsBikers,Indian Trails",
                "daniel.kuperus@gmail.com,UsBikers,Sunshine State Tour",
                "daniel.kuperus@gmail.com,MakeITWork,Vrijdag avond Borrel"};
        reizenAanmaken(csvReis);


        return "redirect:/index";
    }

    private void gebruikersAanmaken(String[] csvGebruiker) {
        String notificatie;
        System.out.println("// Gebruikers aanmaken: ");
        for (int gebruikerIndex = 1; gebruikerIndex < csvGebruiker.length; gebruikerIndex++) {
            String[] csvWaardes = csvGebruiker[gebruikerIndex].split(",");
            Gebruiker testGebruiker = new Gebruiker();
            testGebruiker.setVoornaam(csvWaardes[0]);
            testGebruiker.setAchternaam(csvWaardes[1]);
            testGebruiker.setEmail(csvWaardes[2]);
            testGebruiker.setIdentifier(UUID.randomUUID().toString());
            testGebruiker.setWachtwoord(passwordEncoder.encode(csvWaardes[3]));
            testGebruiker.setRollen(Collections.singletonList(rolRepository.findRolByRolNaam(csvWaardes[4])));
            if (gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()) == null) {
                this.testGebruikers.add(gebruikerRepository.save(testGebruiker));
                notificatie = "Gebruiker toegevoegd: " + testGebruiker.getEmail();
            } else {
                this.testGebruikers.add(gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()));
                notificatie = "Gebruiker bestond al: " + testGebruiker.getEmail();
            }

            System.out.println(notificatie);
        }
    }

    private void groepenAanmaken(String[] csvGroep) {
        String notificatie;
        System.out.println("// Groepen aanmaken: ");
        for (int groepIndex = 1; groepIndex < csvGroep.length; groepIndex++) {
            String[] csvWaardes = csvGroep[groepIndex].split(",");
            Groep groep = new Groep();
            groep.setGroepsNaam(csvWaardes[0]);
            Gebruiker groepBeheerder = gebruikerRepository.findGebruikerByEmail(csvWaardes[1]);
            groep.getGroepsleden().add(groepBeheerder);
            groep.setAanmaker(groepBeheerder.getGebruikersId());

            // Groepsleden toevoegen
            for (int csvWaardesGroepsLeden = 2; csvWaardesGroepsLeden < csvWaardes.length; csvWaardesGroepsLeden++) {
                Gebruiker groepsLid = gebruikerRepository.findGebruikerByEmail(csvWaardes[csvWaardesGroepsLeden]);
                groep.getGroepsleden().add(groepsLid);
            }

            if (groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()) == null) {
                this.testGroepen.add(groepRepository.save(groep));
                notificatie = "Groep toegevoegd: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
            } else {
                this.testGroepen.add(groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()));
                notificatie = "Groep bestond al: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
            }

            System.out.println(notificatie);
        }
    }

    private void reizenAanmaken(String[] csvReis) {
        String notificatie;
        System.out.println("// Reizen aanmaken: ");
        for (int reisIndex = 1; reisIndex < csvReis.length; reisIndex++) {
            String[] csvWaardes = csvReis[reisIndex].split(",");
            Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(csvWaardes[0]);
            Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), csvWaardes[1]);
            ReisItem reis = new ReisItem();
            groep.getReisItem().add(reis);
            reis.setNaam(csvWaardes[2]);
            reis.setAanmaker(beheerder.getGebruikersId());

            if (reisItemRepository.findReisItemByAanmakerAndNaam(reis.getAanmaker(), reis.getNaam()) == null) {
                this.testReizen.add(reisItemRepository.save(reis));
                notificatie = "Reis toegevoegd: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
            } else {
                this.testReizen.add(reisItemRepository.findReisItemByAanmakerAndNaam(reis.getAanmaker(), reis.getNaam()));
                notificatie = "Reis bestond al: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
            }

            System.out.println(notificatie);
        }
    }
}
