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
import java.io.File;
import java.io.FileNotFoundException;
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

    private int testGebruikers = 0;
    private int testGroepen = 0;
    private int testReizen = 0;
    private int testNotities = 0;
    private int testPolls = 0;

    private String notificatie;

    @GetMapping("/testdata")
    public String testDataInladen(Model model) throws InterruptedException {
        notificatie = "\n--// TEST DATA AANMAKEN //--\n";
        System.out.println(notificatie);

        // Gebruikers opslaan
        File gebruikerBestand = new File("Algemeen/Testdata/Gebruiker.csv");
        gebruikersAanmaken(gebruikerBestand);
        entityManager.clear();

        // Groepen opslaan
        File groepBestand = new File("Algemeen/Testdata/Groep.csv");
        groepenAanmaken(groepBestand);
        entityManager.clear();

        // Reizen opslaan
        File reisBestand = new File("Algemeen/Testdata/Reis.csv");
        reizenAanmaken(reisBestand);
        entityManager.clear();

        // Notities opslaan
        File notitieBestand = new File("Algemeen/Testdata/Notitie.csv");
        notitiesAanmaken(notitieBestand);
        entityManager.clear();

        // Polls opslaan
        File pollBestand = new File("Algemeen/Testdata/Poll.csv");
        pollsAanmaken(pollBestand);
        entityManager.clear();

        System.out.println("\nGebruikers ingeladen: " + testGebruikers);
        System.out.println("Groepen ingeladen: " + testGroepen);
        System.out.println("Reizen ingeladen: " + testReizen);
        System.out.println("Notities ingeladen: " + testNotities);
        System.out.println("Polls ingeladen: " + testPolls);


        return "redirect:/index";
    }

    private void gebruikersAanmaken(File gebruikerBestand) {
        System.out.println("// Gebruikers aanmaken: ");
        try {
            Scanner invoer = new Scanner(gebruikerBestand);
            invoer.nextLine();
            while (invoer.hasNextLine()) {
                String[] regelArray = invoer.nextLine().split(",");
                Gebruiker testGebruiker = new Gebruiker();
                testGebruiker.setVoornaam(regelArray[0]);
                testGebruiker.setAchternaam(regelArray[1]);
                testGebruiker.setEmail(regelArray[2]);
                testGebruiker.setIdentifier(UUID.randomUUID().toString());
                testGebruiker.setWachtwoord(passwordEncoder.encode(regelArray[3]));
                testGebruiker.setRollen(Collections.singletonList(rolRepository.findRolByRolNaam(regelArray[4])));

                // Gebruiker opslaan
                if (gebruikerRepository.findGebruikerByEmail(testGebruiker.getEmail()) == null) {
                    this.testGebruikers++;
                    gebruikerRepository.saveAndFlush(testGebruiker);
                    notificatie = "Gebruiker toegevoegd: " + testGebruiker.getEmail();
                } else {
                    notificatie = "Gebruiker bestond al: " + testGebruiker.getEmail();
                }
                System.out.println(notificatie);
            }
        } catch (FileNotFoundException nietGevonden) {
            System.out.println("Gebruikersbestand is niet gevonden (Gebruiker.csv)");
        }
    }

    private void groepenAanmaken(File groepBestand) {
        System.out.println("// Groepen aanmaken: ");
        try {
            Scanner invoer = new Scanner(groepBestand);
            invoer.nextLine();
            while (invoer.hasNextLine()) {
                String[] regelArray = invoer.nextLine().split(",");
                Groep groep = new Groep();
                groep.setGroepsNaam(regelArray[0]);
                Gebruiker groepBeheerder = gebruikerRepository.findGebruikerByEmail(regelArray[1]);
                groep.getGroepsleden().add(groepBeheerder);
                groep.setAanmaker(groepBeheerder.getGebruikersId());

                // Groepsleden toevoegen
                for (int csvWaardesGroepsLeden = 2; csvWaardesGroepsLeden < regelArray.length; csvWaardesGroepsLeden++) {
                    Gebruiker groepsLid = gebruikerRepository.findGebruikerByEmail(regelArray[csvWaardesGroepsLeden]);
                    groep.getGroepsleden().add(groepsLid);
                }

                // Groep opslaan
                if (groepRepository.findByAanmakerAndGroepsNaam(groepBeheerder.getGebruikersId(), groep.getGroepsNaam()) == null) {
                    this.testGroepen++;
                    groepRepository.saveAndFlush(groep);
                    notificatie = "Groep toegevoegd: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
                } else {
                    notificatie = "Groep bestond al: " + groep.getGroepsNaam() + ". Aanmaker: " + groepBeheerder.getEmail() + " Aantal leden: " + groep.getGroepsleden().size();
                }

                System.out.println(notificatie);
            }
        } catch (FileNotFoundException nietGevonden) {
            System.out.println("Gebruikersbestand is niet gevonden (Gebruiker.csv)");
        }
    }

    private void reizenAanmaken(File reisBestand) {
        System.out.println("// Reizen aanmaken: ");
        try {
            Scanner invoer = new Scanner(reisBestand);
            invoer.nextLine();
            while (invoer.hasNextLine()) {
                String[] regelArray = invoer.nextLine().split(",");
                Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(regelArray[0]);
                Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), regelArray[1]);
                ReisItem reis = new ReisItem();
                groep.getReisItem().add(reis);
                reis.setNaam(regelArray[2]);
                reis.setAanmaker(beheerder.getGebruikersId());

                // Reis opslaan
                if (reisItemRepository.findReisItemByAanmakerAndNaam(reis.getAanmaker(), reis.getNaam()) == null) {
                    this.testReizen++;
                    reisItemRepository.saveAndFlush(reis);
                    notificatie = "Reis toegevoegd: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
                } else {
                    notificatie = "Reis bestond al: " + reis.getNaam() + ". Groep: " + groep.getGroepsNaam();
                }

                System.out.println(notificatie);
            }
        } catch (FileNotFoundException nietGevonden) {
            System.out.println("Gebruikersbestand is niet gevonden (Gebruiker.csv)");
        }
    }

    private void notitiesAanmaken(File notitieBestand) {
        System.out.println("// Notities aanmaken: ");
        try {
            Scanner invoer = new Scanner(notitieBestand);
            invoer.nextLine();
            while (invoer.hasNextLine()) {
                String[] regelArray = invoer.nextLine().split(",");
                Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(regelArray[0]);
                Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), regelArray[1]);
                ReisItem reis = reisVanGroepZoeken(groep, regelArray[2]);
                Notitie notitie = new Notitie();
                notitie.setNaam(regelArray[3]);
                notitie.setStartDatum(regelArray[4]);

                // Tekst uitlezen. De tekst mag komma's bevatten, omdat we hier tot het einde van de array kijken, en niet tot de volgende komma.
                StringBuilder notitieTekst = new StringBuilder();
                for (int csvWaardesNotitie = 5; csvWaardesNotitie < regelArray.length; csvWaardesNotitie++) {
                    notitieTekst.append(regelArray[csvWaardesNotitie]);
                }
                notitie.setTekst(notitieTekst.toString());

                // Notitie koppelen
                notitie.setGekoppeldeReisItemId(reis);
                reis.voegReisItemToe(notitie);

                // Notitie opslaan
                if (reisItemRepository.findNotitieByGekoppeldeReisItemAndNaam(reis, notitie.getNaam()) == null) {
                    reisItemRepository.save(notitie);
                    reisItemRepository.save(reis);
                    this.testNotities++;
                    notificatie = "Notitie toegevoegd: " + notitie.getNaam() + ". Reis: " + reis.getNaam();
                } else {
                    notificatie = "Notitie bestond al: " + notitie.getNaam() + ". Reis: " + reis.getNaam();
                }

                System.out.println(notificatie);
            }
        } catch (FileNotFoundException nietGevonden) {
            System.out.println("Gebruikersbestand is niet gevonden (Gebruiker.csv)");
        }
    }

    private void pollsAanmaken(File pollBestand) {
        System.out.println("// Poll aanmaken: ");
        try {
            Scanner invoer = new Scanner(pollBestand);
            invoer.nextLine();
            while (invoer.hasNextLine()) {
                String[] regelArray = invoer.nextLine().split(",");
                Gebruiker beheerder = gebruikerRepository.findGebruikerByEmail(regelArray[0]);
                Groep groep = groepRepository.findByAanmakerAndGroepsNaam(beheerder.getGebruikersId(), regelArray[1]);
                ReisItem reis = reisVanGroepZoeken(groep, regelArray[2]);

                Poll poll = new Poll();
                poll.setNaam(regelArray[3]);
                poll.setStartDatum(regelArray[4]);
                // Poll opties uitlezen. Dit kan meerdere opties, en stemmen bevatten.
                for (int csvWaardesPoll = 5; csvWaardesPoll < regelArray.length; csvWaardesPoll++) {
                    String tekst = regelArray[csvWaardesPoll];

                    if (tekst.startsWith("{optie}")) {
                        // Een optie om op te stemmen wordt toegevoegd.
                        PollOptie optie = new PollOptie();
                        optie.setStemOptie(tekst.replace("{optie}", ""));
                        optie.setOptieIndex(csvWaardesPoll);
                        optie.setPoll(poll);
                        poll.voegPollOptieToe(optie);
                    } else {
                        // Een stemmer wordt toegevoegd aan een net toegevoegde polloptie.
                        String[] stemBiljet = tekst.split("}");
                        stemBiljet[0] = stemBiljet[0].replace("{", "");
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
                    this.testPolls++;
                    notificatie = "Poll toegevoegd: " + poll.getNaam() + ". Reis: " + reis.getNaam() + ". Stemmen: " + poll.geefTotaalAantalStemmen();
                } else {
                    notificatie = "Poll bestond al: " + poll.getNaam() + ". Reis: " + reis.getNaam() + ". Stemmen: " + poll.geefTotaalAantalStemmen();
                }

                System.out.println(notificatie);
            }
        } catch (FileNotFoundException nietGevonden) {
            System.out.println("Gebruikersbestand is niet gevonden (Gebruiker.csv)");
        }
    }


    // De reizen van de groep bijlangs gaan om de juiste reis te vinden.
    private ReisItem reisVanGroepZoeken(Groep groep, String reisNaam) {
        ReisItem gevondenReis = new ReisItem();

        // TODO De reizen van groepen worden niet juist ingeladen, of de testdata is nog niet goed.
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
