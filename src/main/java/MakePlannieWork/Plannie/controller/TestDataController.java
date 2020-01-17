package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.PollOptie;
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
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.Binding;
import javax.persistence.EntityManager;
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
    @Autowired
    EntityManager entityManager;

    private ArrayList<Gebruiker> testGebruikers = new ArrayList<>();
    private ArrayList<Groep> testGroepen = new ArrayList<>();
    private ArrayList<ReisItem> testReizen = new ArrayList<>();
    private ArrayList<Notitie> testNotities = new ArrayList<>();
    private ArrayList<Poll> testPolls = new ArrayList<>();

    private String notificatie;

    @GetMapping("/testdata")
    public String testDataInladen(Model model) throws InterruptedException {
        notificatie = "\n--// TEST DATA AANMAKEN //--\n";
        System.out.println(notificatie);

        // Gebruikers opslaan
        String[] csvGebruiker = {"Voornaam,Achternaam,EMail,Wachtwoord,Rol",
                "Daniel,Kuperus,daniel.kuperus@gmail.com,123,ROLE_USER",
                "Tabitha,Krist,tabitha.krist@gmail.com,123,ROLE_USER",
                "Wouter,Meindertsma,wouter.meindertsma@gmail.com,123,ROLE_USER"};
        gebruikersAanmaken(csvGebruiker);
        entityManager.clear();

        // Groepen opslaan
        String[] csvGroep = {"GroepNaam,GroepBeheerder,LijstGroepsLeden",
                "UsBikers,daniel.kuperus@gmail.com",
                "MakeITWork,daniel.kuperus@gmail.com,tabitha.krist@gmail.com,wouter.meindertsma@gmail.com"};
        groepenAanmaken(csvGroep);
        entityManager.clear();

        // Reizen opslaan
        String[] csvReis = {"GroepBeheerderEmail,GroepNaam,ReisNaam",
                "daniel.kuperus@gmail.com,UsBikers,Indian Trails",
                "daniel.kuperus@gmail.com,UsBikers,Sunshine State Tour",
                "daniel.kuperus@gmail.com,MakeITWork,Vrijdag avond Borrel"};
        reizenAanmaken(csvReis);
        entityManager.clear();

        // Notities opslaan
        String[] csvItemNotitie = {"GroepBeheerderEmail,GroepNaam,ReisNaam,NotitieTitel,NotitieDatum,NotitieTekst",
                "daniel.kuperus@gmail.com,UsBikers,Indian Trails,Testdata uitleg,2020-01-17,Deze notitie is bedoeld om te kijken of deze data invoer werkt.",
                "daniel.kuperus@gmail.com,UsBikers,Sunshine State Tour,Tanken,2020-01-18,Dat is in deze staat erg duurt.",
                "daniel.kuperus@gmail.com,UsBikers,Sunshine State Tour,Hotels,2020-01-19,Het minimum loon is omhoog gegaan."};
        notitiesAanmaken(csvItemNotitie);
        entityManager.clear();

        // Polls opslaan
        String[] csvItemPoll = {"GroepBeheerderEmail,GroepNaam,ReisNaam,PollTitel,PollDatum,{optie}LijstPollOpties,{LijstPollOpties}LijstPollStemmen",
                "daniel.kuperus@gmail.com,MakeITWork,Vrijdag avond Borrel,Mag wouter drinken,2020-01-17,{optie}Ja,{optie}Misschien,{optie}Nee,{Ja}tabitha.krist@gmail.com,{Ja}wouter.meindertsma@gmail.com,{Misschien}daniel.kuperus@gmail.com",
                "daniel.kuperus@gmail.com,UsBikers,Sunshine State Tour,Overnachting Las Vegas,2020-01-17,{optie}Stratosphere,{optie}WigWam Motel,{optie}Super8,{optie}Blue Swallow Motel"};
        pollsAanmaken(csvItemPoll);
        entityManager.clear();

        System.out.println("\nGebruikers ingeladen: " + testGebruikers.size());
        System.out.println("Groepen ingeladen: " + testGroepen.size());
        System.out.println("Reizen ingeladen: " + testReizen.size());
        System.out.println("Notities ingeladen: " + testNotities.size());
        System.out.println("Polls ingeladen: " + testPolls.size());


        return "redirect:/index";
    }

    private void gebruikersAanmaken(String[] csvGebruiker) {
        System.out.println("// Gebruikers aanmaken: ");
        for (int gebruikerIndex = 1; gebruikerIndex < csvGebruiker.length; gebruikerIndex++) {
            // Gebruiker aanmaken
            String[] csvWaardes = csvGebruiker[gebruikerIndex].split(",");
            Gebruiker testGebruiker = new Gebruiker();
            testGebruiker.setVoornaam(csvWaardes[0]);
            testGebruiker.setAchternaam(csvWaardes[1]);
            testGebruiker.setEmail(csvWaardes[2]);
            testGebruiker.setIdentifier(UUID.randomUUID().toString());
            testGebruiker.setWachtwoord(passwordEncoder.encode(csvWaardes[3]));
            testGebruiker.setRollen(Collections.singletonList(rolRepository.findRolByRolNaam(csvWaardes[4])));

            // Gebruiker opslaan
            if (gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()) == null) {
                this.testGebruikers.add(gebruikerRepository.saveAndFlush(testGebruiker));
                notificatie = "Gebruiker toegevoegd: " + testGebruiker.getEmail();
            } else {
                notificatie = "Gebruiker bestond al: " + testGebruiker.getEmail();
            }

            System.out.println(notificatie);
        }
    }

    private void groepenAanmaken(String[] csvGroep) {
        System.out.println("// Groepen aanmaken: ");
        for (int groepIndex = 1; groepIndex < csvGroep.length; groepIndex++) {
            // Groep aanmaken
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

            // Groep opslaan
            if (groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()) == null) {
                this.testGroepen.add(groepRepository.saveAndFlush(groep));
                notificatie = "Groep toegevoegd: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
            } else {
                notificatie = "Groep bestond al: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
            }

            System.out.println(notificatie);
        }
    }

    private void reizenAanmaken(String[] csvReis) {
        System.out.println("// Reizen aanmaken: ");
        for (int reisIndex = 1; reisIndex < csvReis.length; reisIndex++) {
            // Reis aanmaken
            String[] csvWaardes = csvReis[reisIndex].split(",");
            Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(csvWaardes[0]);
            Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), csvWaardes[1]);
            ReisItem reis = new ReisItem();
            groep.getReisItem().add(reis);
            reis.setNaam(csvWaardes[2]);
            reis.setAanmaker(beheerder.getGebruikersId());

            // Reis opslaan
            if (reisItemRepository.findReisItemByAanmakerAndNaam(reis.getAanmaker(), reis.getNaam()) == null) {
                this.testReizen.add(reisItemRepository.saveAndFlush(reis));
                reisItemRepository.flush();
                notificatie = "Reis toegevoegd: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
            } else {
                notificatie = "Reis bestond al: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
            }

            System.out.println(notificatie);
        }
    }

    private void notitiesAanmaken(String[] csvItemNotitie) {
        System.out.println("// Notities aanmaken: ");
        for (int reisIndex = 1; reisIndex < csvItemNotitie.length; reisIndex++) {
            // Notitie aanmaken
            String[] csvWaardes = csvItemNotitie[reisIndex].split(",");
            Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(csvWaardes[0]);
            Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), csvWaardes[1]);
            ReisItem reis = reisVanGroepZoeken(groep, csvWaardes[2]);
            Notitie notitie = new Notitie();
            notitie.setNaam(csvWaardes[3]);
            notitie.setStartDatum(csvWaardes[4]);

            // Tekst uitlezen. De tekst mag komma's bevatten, omdat we hier tot het einde van de array kijken, en niet tot de volgende komma.
            StringBuilder notitieTekst = new StringBuilder();
            for (int csvWaardesNotitie = 5; csvWaardesNotitie < csvWaardes.length; csvWaardesNotitie++) {
                notitieTekst.append(csvWaardes[csvWaardesNotitie]);
            }
            notitie.setTekst(notitieTekst.toString());

            // Notitie koppelen
            notitie.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(notitie);

            // Notitie opslaan
            if (reisItemRepository.findNotitieByGekoppeldeReisItemAndNaam(reis, notitie.getNaam()) == null) {
                reisItemRepository.save(notitie);
                reisItemRepository.save(reis);
                this.testNotities.add(reisItemRepository.findNotitieByGekoppeldeReisItemAndNaam(reis, notitie.getNaam()));
                notificatie = "Notitie toegevoegd: " + notitie.getNaam() + ". Reis: " + reis.getNaam();
            } else {
                notificatie = "Notitie bestond al: " + notitie.getNaam() + ". Reis: " + reis.getNaam();
            }

            System.out.println(notificatie);
        }
    }

    private void pollsAanmaken(String[] csvItemPoll) {
        System.out.println("// Poll aanmaken: ");
        for (int reisIndex = 1; reisIndex < csvItemPoll.length; reisIndex++) {
            // Poll aanmaken
            String[] csvWaardes = csvItemPoll[reisIndex].split(",");
            Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(csvWaardes[0]);
            Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), csvWaardes[1]);
            ReisItem reis = reisVanGroepZoeken(groep, csvWaardes[2]);

            Poll poll = new Poll();
            poll.setNaam(csvWaardes[3]);
            poll.setStartDatum(csvWaardes[4]);
            // Poll opties uitlezen. Dit kan meerdere opties, en stemmen bevatten.
            for (int csvWaardesPoll = 5; csvWaardesPoll < csvWaardes.length; csvWaardesPoll++) {
                String tekst = csvWaardes[csvWaardesPoll];

                if (tekst.startsWith("{optie}")) {
                    // Een optie om op te stemmen wordt toegevoegd.
                    PollOptie optie = new PollOptie();
                    optie.setStemOptie(tekst.replace("{optie}",""));
                    optie.setOptieIndex(csvWaardesPoll);
                    optie.setPoll(poll);
                    poll.voegPollOptieToe(optie);
                } else {
                    // Een stemmer wordt toegevoegd aan een net toegevoegde polloptie.
                    String[] stemBiljet = tekst.split("}");
                    stemBiljet[0] = stemBiljet[0].replace("{","");
                    Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(stemBiljet[1]);

                    poll.voegPollStemToe(stemBiljet[0], gebruiker);
                }
            }

            // Poll koppelen
            poll.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(poll);

            // Poll opslaan
            if (reisItemRepository.findPollByGekoppeldeReisItemAndNaam(reis, poll.getNaam()) == null) {
                reisItemRepository.save(poll);
                reisItemRepository.save(reis);
                this.testPolls.add(reisItemRepository.findPollByGekoppeldeReisItemAndNaam(reis, poll.getNaam()));
                notificatie = "Poll toegevoegd: " + poll.getNaam() + ". Reis: " + reis.getNaam() + ". Stemmen: " + poll.geefTotaalAantalStemmen();
            } else {
                notificatie = "Poll bestond al: " + poll.getNaam() + ". Reis: " + reis.getNaam() + ". Stemmen: " + poll.geefTotaalAantalStemmen();
            }

            System.out.println(notificatie);
        }
    }


    // De reizen van de groep bijlangs gaan om de juiste reis te vinden.
    private ReisItem reisVanGroepZoeken(Groep groep, String reisNaam) {
        ReisItem gevondenReis = new ReisItem();
        Iterator<ReisItem> reizen = groep.getReisItem().iterator();
        while (reizen.hasNext() && gevondenReis.getNaam() == null) {
            ReisItem controleReisItem = reizen.next();
            if (controleReisItem.getNaam().equals(reisNaam)) {
                gevondenReis = controleReisItem;
            }
        }
        return gevondenReis;
    }
}
