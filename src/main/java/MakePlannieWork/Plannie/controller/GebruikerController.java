package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerVerificatieRepository;
import MakePlannieWork.Plannie.repository.RolRepository;
import MakePlannieWork.Plannie.repository.WachtwoordResetRepository;
import MakePlannieWork.Plannie.service.PlannieGebruikerSecurityService;
import MakePlannieWork.Plannie.service.PlannieGebruikersService;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieMailingService;
import MakePlannieWork.Plannie.util.GenericResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private GebruikerVerificatieRepository gebruikerVerificatieRepository;

    @Autowired
    private PlannieGebruikerSecurityService plannieGebruikerSecurityService;

    @Autowired
    private MessageSource messages;

    @Autowired
    PlannieMailingService plannieMailingService;

    @GetMapping({"/index" , "/"})
    String index(Model model, Principal principal) {
        model.addAttribute("loginForm", new Gebruiker());
        model.addAttribute("updatePasswordForm", new Gebruiker());
        if (principal != null && !principal.getName().equals("")) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
        }
        return "index";
    }

    @GetMapping("/registreren")
        public String registreren(@RequestParam(name="gebruikerUUID", required = false) String gebruikerUUID, Model model) {
        model.addAttribute("registratieFormulier", new Gebruiker());
        model.addAttribute("loginForm", new Gebruiker());
        model.addAttribute("updatePasswordForm", new Gebruiker());
        if (gebruikerUUID != null) {
            model.addAttribute("gebruikerUUID", gebruikerUUID);

        }
        return "gebruikerNieuw";
    }


    public String registratieGebruiker(Gebruiker gebruiker, HttpServletRequest request) {

        List<Gebruiker> bestaandeGebruiker = gebruikerRepository.findGebruikersByEmail(gebruiker.getEmail());
        Gebruiker gebruikerZonderToken = gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail());

        // Is het een bestaande gebruiker?
        if (!bestaandeGebruiker.isEmpty() || !gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            // Staat enabled op true?
            if (gebruikerZonderToken.isEnabled()) {
                return "gebruikerBestaat";
            } else if (gebruikerVerificatieRepository.findByGebruiker(gebruikerZonderToken) != null){
                return "tokenLooptNog";
            // maak een random token aan
            } else {
                maakTokenAan(gebruikerZonderToken, request);
                return "tokenNieuw";
            }
        // Als het geen bestaande gebruiker is maak een gebruiker aan met een random token
        } else {
            maakGebruikerAan(gebruiker);
            // maak een random token aan
            maakTokenAan(gebruiker, request);
            return "gebruikerGeregistreerd";
        }
    }

    public void maakTokenAan(Gebruiker gebruiker, HttpServletRequest request) {
        final String token = UUID.randomUUID().toString();
        plannieGebruikersService.maakGebruikerVerificatieToken(gebruiker, token);
        try {
            plannieMailingService.maakGebruikerVerificatieTokenEmail(plannieMailingService.getAppUrl(request), request.getLocale(), token, gebruiker);
        } catch (MessagingException execption) {
            execption.printStackTrace();
        }
    }

    public void maakGebruikerAan(Gebruiker gebruiker) {
        gebruiker.setIdentifier(UUID.randomUUID().toString());
        gebruiker.setRollen(Arrays.asList(rolRepository.findRolByRolNaam("ROLE_USER")));
        gebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
        gebruiker.setEnabled(false);
        gebruikerRepository.save(gebruiker);
    }

    @PostMapping("/registreren/controle")
    public ResponseEntity<Object> nieuweGebruiker(@RequestBody String gebruikerString, HttpServletRequest request) throws JSONException {
        JSONObject jsonGebruiker = new JSONObject(gebruikerString);
        Gebruiker nieuweGebruiker = new Gebruiker();
        nieuweGebruiker.setVoornaam(jsonGebruiker.getString("voornaam"));
        nieuweGebruiker.setAchternaam(jsonGebruiker.getString("achternaam"));
        nieuweGebruiker.setEmail(jsonGebruiker.getString("email"));
        nieuweGebruiker.setWachtwoord(jsonGebruiker.getString("wachtwoord"));
        nieuweGebruiker.setTrancientWachtwoord(jsonGebruiker.getString("trancientWachtwoord"));
        return new ResponseEntity<Object>(registratieGebruiker(nieuweGebruiker, request), HttpStatus.OK);
    }

    @GetMapping("/maakRegistratieCompleet")
    public String showMaakRegistratieCompleetPagina(final Model model, @RequestParam("id") final String id,
                                                    @RequestParam("token") final String token) {

        final String result = plannieGebruikerSecurityService.valideerGebruikerVerificatieToken(id, token);
        if (result != null) {
            return "redirect:/error";
        }
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByIdentifier(id);
        model.addAttribute("maakRegistratieCompleetFormulier", new Gebruiker());
        model.addAttribute("loginForm", new Gebruiker());
        model.addAttribute(gebruiker);
        return "gebruikerValideren";
    }

    @PostMapping("/{identifier}/saveGebruiker")
    public String saveGebruiker(@PathVariable("identifier") String identifier, Gebruiker gebruiker) {
        final Gebruiker huidigeGebruiker = gebruikerRepository.findGebruikerByIdentifier(identifier);
        if (!huidigeGebruiker.getEnabled()) {
            huidigeGebruiker.setEnabled(true);
            gebruikerRepository.save(huidigeGebruiker);
        }
        gebruikerVerificatieRepository.delete(gebruikerVerificatieRepository.findByGebruiker(huidigeGebruiker));
        if (huidigeGebruiker.getVoornaam().equals(huidigeGebruiker.getEmail())) {
            return "redirect:/gebruikerWijzig";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/gebruikerDetail")
    public String gebruikerDetail(Model model, Principal principal) {
        if (!plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName()).isEmpty() || plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName()) != null){
            Set<Groep> groepen = plannieGroepService.getLijstMetGroepenOpGebruikersnaam(principal.getName());
            ArrayList<Groep> gesorteerdeGroepen = new ArrayList<>(groepen);
            gesorteerdeGroepen.sort(Comparator.comparing(Groep::getGroepId));
            model.addAttribute("lijstMetGroepen", gesorteerdeGroepen);
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
    public String resetWachtwoord(HttpServletRequest request, @ModelAttribute("updatePasswordForm") Gebruiker gebruiker) throws MessagingException {
        // een gebruiker vinden aan de hand van email adres
        Gebruiker gebruikerZonderWachtwoord = gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail());

        // Als gebruiker geen wachtwoordresettoken heeft
        if (wachtwoordResetRepository.findByGebruiker(gebruikerZonderWachtwoord) == null) {
            // als het een bestaande gebruiker is
            if (gebruikerZonderWachtwoord != null) {
                // maak een random token aan
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
        } else {
            plannieMailingService.maakWachtwoordResetTokenEmail(plannieMailingService.getAppUrl(request), request.getLocale(),
                    wachtwoordResetRepository.findByGebruiker(gebruikerZonderWachtwoord).getToken(), gebruikerZonderWachtwoord);
            return "redirect:/index";
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
            wachtwoordResetRepository.delete(wachtwoordResetRepository.findByGebruiker(huidigeGebruiker));
            return "redirect:/index";

        } else {
            return "redirect:/gebruikerWachtwoordUpdate";
        }
    }
}
