package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.service.PlannieGebruikersService;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieMailingService;
import MakePlannieWork.Plannie.service.PlannieReisItemService;
import org.apache.jasper.tagplugins.jstl.core.Url;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class GroepController {

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private PlannieGebruikersService plannieGebruikersService;

    @Autowired
    private PlannieReisItemService plannieReisItemService;

    @Autowired
    private PlannieMailingService plannieMailingService;

    @PostMapping("/groepAanmaken")
    public String nieuweGroep(Groep groep, Model model, Principal principal) {
        if (groep != null && !groep.getGroepsNaam().isEmpty()) {
            model.addAttribute(groep);
            plannieGroepService.voegGroepToe(groep, principal);
            return "redirect:/groepDetail/" + groep.getGroepId();
        }
        return "error";
    }

    @GetMapping("/groepDetail/{groepId}")
    public String groepDetail(@PathVariable("groepId") Integer id, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(id);
        if (groepOptional.isPresent()) {
            model.addAttribute("groep", groepOptional.get());

            Set<ReisItem> alleReisItems = plannieReisItemService.getLijstMetReisItemsOpGroepId(id);
            ArrayList<ReisItem> gesorteerdeReizen = new ArrayList<>(alleReisItems);
            gesorteerdeReizen.sort(Comparator.comparing(ReisItem::getEindDatum).thenComparing(ReisItem::getNaam));
            model.addAttribute("lijstMetReisItems", gesorteerdeReizen);

            Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
            model.addAttribute("currentUser", gebruiker);

            List<Gebruiker> alleGebruikersInGroep = gebruikerRepository.findByGroepen_groepId(id);
            model.addAttribute("groepsLedenLijst", alleGebruikersInGroep);

            ReisItem reisItem = new ReisItem();
            reisItem.geefNieuwStartDatum();
            model.addAttribute("nieuwReisItemFormulier", reisItem);
            return "groepDetail";
        }
        return "redirect:/gebruikerDetail";
    }

    @GetMapping("/groepDetail/{groepId}/voegLedenToeAanGroepViaEmail")
    public String voegLedenToeAanGroepViaEmail(Model model, HttpServletRequest request) {
        model.addAttribute("groepslidEmail", new Gebruiker());
        return "gebruikerNieuw";
    }

    @PostMapping("/groepDetail/{groepId}/voegLedenToeAanGroepViaEmail")
    public String voegLedenToeAanGroepViaEmail(@PathVariable("groepId") Integer groepId, @ModelAttribute("groepslidEmail") Gebruiker gebruiker, BindingResult result, Model model, HttpServletRequest request) throws MessagingException {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        if (!groepOptional.isPresent()) {
            result.reject("Invalid group");
        }
        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            return "groepDetail";
        } else {
            gebruiker.setIdentifier(UUID.randomUUID().toString());
            gebruiker.setVoornaam(gebruiker.getEmail());
            gebruikerRepository.save(gebruiker);
            final String token = UUID.randomUUID().toString();
            plannieGebruikersService.maakGebruikerVerificatieToken(gebruiker, token);
            plannieGroepService.voegGebruikerToeAanGroep(gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail()).getGebruikersId(), groepId);
            try {
                plannieMailingService.maakGebruikerVerificatieTokenEmail(plannieMailingService.getAppUrl(request), request.getLocale(), token, gebruiker);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/groepDetail/" + groepOptional.get().getGroepId();
    }

    // Gebruiker gaat naar scherm waar naam van groep gewijzigd kan worden
    //@PostAuthorize("returnObject.owner == principal.username")
    @GetMapping("/groepDetail/{groepId}/groepWijzig")
    @PostAuthorize("hasPermission(id, 'MakePlannieWork.Plannie.model.Groep', 'view')")
    public String huidigeGroep(@PathVariable("groepId") Integer id, Model model) {
        Optional<Groep> groepOptional = plannieGroepService.findById(id);
        model.addAttribute("groep", groepOptional.get());
        model.addAttribute("groepsNaamWijzigingsFormulier", new Groep());
        return "groepWijzig";
    }

    @PostMapping("/groepDetail/{groepId}/groepWijzig")
    public String wijzigenGroepsNaam(@ModelAttribute("groepsNaamWijzigingsFormulier")
                                             Groep groep, @PathVariable("groepId") Integer groepId, BindingResult result) {
        if (result.hasErrors()) {
            return "groepDetail";
        } else {
            Groep huidigeGroep = groepRepository.findByGroepId(groepId);
            huidigeGroep.setGroepsNaam(groep.getGroepsNaam());
            groepRepository.save(huidigeGroep);
            return "redirect:/groepDetail/" + groepId;
        }
    }

    // Wijzig de beheerder van de groep.
    @PostMapping("/groepDetail/{groepId}/groepBeheerderWijzig")
    public String wijzigenGroepsBeheerder(@RequestParam("beheerderEmail") String beheerderEmail, @PathVariable("groepId") Integer groepId) {

        Groep huidigeGroep = groepRepository.findByGroepId(groepId);
        Gebruiker nieuweBeheerder = gebruikerRepository.findGebruikerByEmail(beheerderEmail);
        huidigeGroep.setAanmaker(nieuweBeheerder.getGebruikersId());
        groepRepository.save(huidigeGroep);

        return "redirect:/groepDetail/" + groepId;
    }

    // Bestanden uploaden
    // TODO Deze controller is voor nu vooral bedoeld als stub.
    // TODO Alhoewel je vanuit een groep al wel een plaatje kan selecteren, en uploaden, missen er nog wel een 2 functionaliteiten:
    // TODO 1: Controle op het soort bestand dat geupload wordt. (Alleen jpg, of png, of kunnen beiden?)
    // TODO 2: Het laten zien van de geuploade image, als groepsafbeelding.
    @PostMapping("/{groepId}/uploadImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable("groepId") Integer groepId) {
        Groep huidigeGroep = groepRepository.findByGroepId(groepId);
        try {
            plannieGroepService.saveImage(imageFile, huidigeGroep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/groepDetail/" + groepId;
    }

    @RequestMapping(value = "/{groepId}/saveCroppedImage/{imageURL}")
    @ResponseBody
    public void saveCroppedImage(@PathVariable("groepId") Integer groepId, @PathVariable("imageURL") String imageURL) throws IOException {
        Groep huidigeGroep = groepRepository.findByGroepId(groepId);
        BufferedImage image = ImageIO.read(new File(imageURL));
        ImageIO.write(image, "jpg", new File("huidigeGroep.getImagePath()"));

    }

    // Gebruikers zoeken
    @RequestMapping(value = "/{groepId}/zoekGebruikers")
    @ResponseBody
    public List<Gebruiker> gebruikers(@RequestParam(value = "term", required = false, defaultValue = "") String term, @PathVariable("groepId") Integer groepId) {
        List<Gebruiker> suggestions = new ArrayList<Gebruiker>();
        List<Gebruiker> alleGebruikers = gebruikerRepository.findGebruikers(term, groepId);
        suggestions.addAll(alleGebruikers);

        return suggestions;
    }

    @RequestMapping("/{groepId}/groepsLeden")
    @ResponseBody
    public List<Gebruiker> findGroepsLeden(@PathVariable("groepId") Integer groepId) {
        List<Gebruiker> suggestions = new ArrayList<>();
        List<Gebruiker> alleGroepsLeden = gebruikerRepository.findByGroepen_groepId(groepId);
        suggestions.addAll(alleGroepsLeden);
        return suggestions;
    }

    @RequestMapping("/{groepId}/VerwijderLedenUitGroep/{gebruikersId}")
    @ResponseBody
    public void VerwijderLedenUitGroepAjax(@PathVariable("groepId") Integer groepId, @PathVariable("gebruikersId") Integer gebruikersId, Principal principal) {
        plannieGroepService.verwijderGebruikerUitGroep(gebruikersId, groepId);
    }

    @RequestMapping("/groepDetail/{groepId}/voegGebruikerToeAanGroep/{gebruikersId}")
    @ResponseBody
    public void voegGebruikerToeAanGroep(@PathVariable("groepId") Integer groepId, @PathVariable("gebruikersId") Integer gebruikersId, Principal principal) {
        plannieGroepService.voegGebruikerToeAanGroep(gebruikersId, groepId);
    }
}
