package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.RolRepository;
import MakePlannieWork.Plannie.repository.WachtwoordResetRepository;
import MakePlannieWork.Plannie.service.PlannieGebruikerSecurityService;
import MakePlannieWork.Plannie.service.PlannieGebruikersService;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieMailingService;
import MakePlannieWork.Plannie.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import MakePlannieWork.Plannie.repository.GebruikerRepository;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.*;

@Controller
public class GebruikerController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PlannieGebruikersService plannieGebruikersService;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private WachtwoordResetRepository wachtwoordResetRepository;

    @Autowired
    private PlannieGebruikerSecurityService plannieGebruikerSecurityService;

    @Autowired
    private MessageSource messages;

    @Autowired
    PlannieMailingService plannieMailingService;

    @GetMapping({"/index" , "/"})
    String index(Model model) {
        model.addAttribute("loginForm", new Gebruiker());
        model.addAttribute("updatePasswordForm", new Gebruiker());
        return "index";
    }

    @GetMapping("/registreren")
        public String registreren(@RequestParam(name="gebruikerUUID", required = false) String gebruikerUUID, Model model) {
        model.addAttribute("registratieFormulier", new Gebruiker());
        model.addAttribute("loginForm", new Gebruiker());
        if (gebruikerUUID != null) {
            model.addAttribute("gebruikerUUID", gebruikerUUID);

        }
        return "gebruikerNieuw";
    }

    @PostMapping("/registreren")
    public String nieuweGebruiker(@ModelAttribute("registratieformulier") Gebruiker gebruiker, Model model, BindingResult result) {
        // Als de ingevulde gebruiker al best in de database bestaat met dit email adres, wordt de actie niet uitgevoerd.
        List<Gebruiker> bestaandeGebruiker = gebruikerRepository.findGebruikersByEmail(gebruiker.getEmail());
        model.addAttribute("updatePasswordForm", new Gebruiker());

        if (!bestaandeGebruiker.isEmpty() || result.hasErrors() || !gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            model.addAttribute("registratieFormulier", new Gebruiker());
            model.addAttribute("loginForm", new Gebruiker());
            return "gebruikerNieuw";
        } else {
            gebruiker.setIdentifier(UUID.randomUUID().toString());
            gebruiker.setRollen(Arrays.asList(rolRepository.findRolByRolNaam("ROLE_USER")));
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

    // Gebruiker gaat naar gebruikerWijzig URL en zijn gegevens worden ingevuld in gebruikersWijzigingsFormulier
    @GetMapping("/gebruikerWijzig")
    public String huidigeGebruiker(Model model, Principal principal) {
        model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
        model.addAttribute("gebruikersWijzigingsFormulier", new Gebruiker());
        return "gebruikerWijzig";
    }

    // Gebruiker kan zijn voornaam, achternaam en email adres wijzigen
    // Zijn nieuwe gegevens worden onder zijn eigen gebruikersId opgeslagen
    // Gebruiker gaat weer naar gebruikerWijzig met zijn nieuwe gegevens
    @PostMapping("/wijzigen")
    public String wijzigenHuidigeGebruiker(@ModelAttribute("gebruikersWijzigingsFormulier") Gebruiker gebruiker,
                                    BindingResult result, Principal principal) {
        if (result.hasErrors() || !gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            // TODO Als de gebruiker een niet matchend wachtwoord heeft, wordt hij nu zonder foutmelding teruggeleid naar de pagina.
            return "redirect:/gebruikerWijzig";
        } else {
            Gebruiker huidigeGebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());

            // Als het wachtwoord, en het bevestigde wachtwoord matchen, wordt deze opgeslagen voor de gebruiker.
            if (!gebruiker.getWachtwoord().equals("") && !gebruiker.getTrancientWachtwoord().equals("")) {
                huidigeGebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
            }

            huidigeGebruiker.setVoornaam(gebruiker.getVoornaam());
            huidigeGebruiker.setAchternaam(gebruiker.getAchternaam());
            huidigeGebruiker.setEmail(gebruiker.getEmail());

            gebruikerRepository.save(huidigeGebruiker);
            return "redirect:/gebruikerDetail";
        }
    }

    @PostMapping("/wachtwoordReset")
    public String resetWachtwoord(HttpServletRequest request, @ModelAttribute("updatePasswordForm") Gebruiker gebruiker) {
        Gebruiker gebruikerZonderWachtwoord = gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail());
        if (gebruikerZonderWachtwoord.getEmail() != null) {
            final String token = UUID.randomUUID().toString();
            plannieGebruikersService.maakWachtWoordResetTokenVoorGebruiker(gebruikerZonderWachtwoord, token);
            try {
                plannieMailingService.maakWachtwoordResetTokenEmail(plannieMailingService.getAppUrl(request), request.getLocale(), token, gebruikerZonderWachtwoord);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            return "redirect:/index";
        } else {
            return "/error";
        }
    }

    @GetMapping("/wijzigWachtwoord")
    public String showWijzigWachtwoordPagina(final Model model, @RequestParam("id") final String id, @RequestParam("token") final String token) {


        final String result = plannieGebruikerSecurityService.valideerWachtwoordResetToken(id, token);
        if (result != null) {

            return "redirect:/error";
        }
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByIdentifier(id);
        model.addAttribute("wachtwoordUpdateFormulier", new Gebruiker());
        model.addAttribute("loginForm", new Gebruiker());
        model.addAttribute(gebruiker);
        return "gebruikerWachtwoordUpdate";
    }

    @PostMapping("/{identifier}/saveWachtwoord")
    public String savePassword(@PathVariable("identifier") String identifier, Gebruiker gebruiker) {
        final Gebruiker huidigeGebruiker = gebruikerRepository.findGebruikerByIdentifier(identifier);
        if (gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            huidigeGebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
            gebruikerRepository.save(huidigeGebruiker);
            return "redirect:/index";
        } else {
            return "redirect:/gebruikerWachtwoordUpdate";
        }
    }
}
