package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import MakePlannieWork.Plannie.repository.GebruikerRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class GebruikerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @GetMapping({"/index" , "/"})
    String index(Model model) {
        model.addAttribute("loginForm", new Gebruiker());
        return "index";
    }

    @GetMapping("/registreren")
        public String registreren(@RequestParam(name="groepUUID", required = false) String groepUUID, Model model) {
        model.addAttribute("registratieFormulier", new Gebruiker());
        if (groepUUID != null) {
            model.addAttribute("groepUUID", groepUUID);
        }
        return "gebruikerNieuw";
    }

    @PostMapping("/registreren")
    public String nieuweGebruiker(@ModelAttribute("registratieformulier") Gebruiker gebruiker, Model model, BindingResult result) {
        // Als de ingevulde gebruiker al best in de database bestaat met dit email adres, wordt de actie niet uitgevoerd.
        List<Gebruiker> bestaandeGebruiker = gebruikerRepository.findGebruikersByEmail(gebruiker.getEmail());

        if (!bestaandeGebruiker.isEmpty() || result.hasErrors() || !gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            model.addAttribute("registratieFormulier", new Gebruiker());
            return "gebruikerNieuw";
        } else {
            gebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
            gebruikerRepository.save(gebruiker);
            model.addAttribute("loginForm", new Gebruiker());
            return "index";
        }
    }

    @GetMapping("/gebruikerDetail")
    public String gebruikerDetail(Model model, Principal principal) {
        if (!plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName()).isEmpty() || plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName()) != null){
            Set<Groep> groepen = plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName());
            model.addAttribute("lijstMetGroepen", groepen);
        }
        model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
        return "gebruikerDetail";
    }

    @GetMapping("/gebruikerWijzig")
    public String gebruikerWijzig(Model model, Principal principal) {
        model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
        model.addAttribute("gebruikersWijzigingsFormulier", new Gebruiker());
        return "gebruikerWijzig";
    }

    @PostMapping("/wijzigen")
    public String wijzigenGebruiker(@ModelAttribute("gebruikersWijzigingsFormulier") Gebruiker gebruiker, Principal principal) {
        Gebruiker huidigeGebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        System.out.println("Nieuwe gebruiker:" + gebruiker.getVoornaam());
        System.out.println("Huidige gebruiker: " + huidigeGebruiker.getGebruikersId() + " " + huidigeGebruiker.getVoornaam());
        huidigeGebruiker.setVoornaam(gebruiker.getVoornaam());
        huidigeGebruiker.setAchternaam(gebruiker.getAchternaam());
        huidigeGebruiker.setEmail(gebruiker.getEmail());
        System.out.println("Huidige gebruiker andere voornaa: " + huidigeGebruiker.getVoornaam());
        gebruikerRepository.save(huidigeGebruiker);
        return "gebruikerDetail";
    }

}
