package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieReisItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.criteria.CriteriaBuilder;
import java.security.Principal;
import java.util.Optional;
import java.util.Set;

@Controller
public class ReisItemController {

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private ReisItemRepository reisItemRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private PlannieReisItemService plannieReisItemService;

    // Vanuit een Groep een Reis aanmaken
    @PostMapping("{groepId}/reisItemAanmaken")
    public String nieuwReisItem(ReisItem reisItem, @PathVariable("groepId") Integer groepId, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        if (reisItem != null && !reisItem.getNaam().isEmpty()) {
            plannieReisItemService.voegReisItemToe(groepOptional.get(), reisItem, principal);
            return "redirect:/" + groepId + "/reisItemDetail/" + reisItem.getReisItemId();
        }
        return "error";
    }

    // Vanuit een ReisItem de naam van het ReisItem wijzigen
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/reisNaamWijzigen")
    public String reisNaamWijzigen(@ModelAttribute("reisNaamWijzigingsFormulier")
                                           ReisItem reisItem, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, BindingResult result) {
        if (result.hasErrors()) {
            return "groepDetail";
        } else {
            ReisItem huidigReisItem = reisItemRepository.findGebruikerByReisItemId(reisItemId);
            huidigReisItem.setNaam(reisItem.getNaam());
            reisItemRepository.save(huidigReisItem);
            return "redirect:/" + groepId + "/reisItemDetail/" + reisItem.getReisItemId();
        }
    }

    // Klaarzetten van het Reis Detail Overzicht
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}")
    public String reisItemDetail(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);
        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("alleReisItemsVanReis", reisItemOptional.get().getReisItems());
            return "reisItemDetail";
        }
        return "redirect:/groepDetail";
    }

    // Ga naar pagina waar je notitie kan aanmaken
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/NotitieAanmaken")
    public String notitieAanmaken(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());

            model.addAttribute("notitieAanmakenFormulier", new Notitie());
            return "notitieNieuw";
        }

        return "reisItemDetail";
    }

    // Nieuwe notitie opslaan
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuweNotitie")
    public String notitieOpslaan(@ModelAttribute("notitieAanmakenFormulier") @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Notitie notitie, Principal principal, BindingResult result) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);

        if (reisItemOptional.isPresent() && !notitie.getTekst().equals("")) {
            ReisItem reis = reisItemOptional.get();

            System.out.println("Voor add " + reis.getNaam());

            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reis);
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", reis);

            System.out.println("Na add " + reis.getNaam());

            // ReisItem aan reis koppelen, en ReisItem aan reis toevoegen.
            notitie.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(notitie);



            reisItemRepository.save(notitie);
            reisItemRepository.save(reis);
            System.out.println("na opslaan notitie " + notitie.getNaam());
        }

        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Klaarzetten van het Notitie wijzigen Overzicht
    @GetMapping("/{groepId}/{reisItemId}/{reisItemsId}/NotitieWijzigen")
    public String huidigeNotitie(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, @PathVariable("reisItemsId") Integer notitieId, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);
        model.addAttribute("reisItems", reisItemRepository.findReisItemByReisItemId(notitieId));
        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("notitieWijzigingsFormulier", new Notitie());
            return "notitieWijzig";
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    @PostMapping("/{groepId}/{reisItemId}/{reisItemsId}/notitieWijzigen")
    public String notitieWijzigen(@ModelAttribute("notitieWijzigingsFormulier") Notitie notitie, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                  @PathVariable("reisItemsId") Integer notitieId, BindingResult result, Principal principal, Model model) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);
        ReisItem reis = reisItemOptional.get();
        model.addAttribute("reisItem", reis);
        model.addAttribute("groep", reis);
        model.addAttribute("reisItems", reisItemRepository.findReisItemByReisItemId(notitieId));

        if (result.hasErrors()) {
            return "redirect:/notitieWijzig";
        } else {
            Notitie huidigeNotitie = reisItemRepository.findReisItemByReisItemId(notitieId);
            System.out.println("Voor save notitienaam = " + huidigeNotitie.getNaam());
            System.out.println("Voor save notitietekst = " + huidigeNotitie.getTekst());
            System.out.println("Voor save notitiedatum = " + huidigeNotitie.getStartDatum());
            huidigeNotitie.setNaam(notitie.getNaam());
            huidigeNotitie.setTekst(notitie.getTekst());
            huidigeNotitie.setStartDatum(notitie.getStartDatum());
            reisItemRepository.save(huidigeNotitie);

            System.out.println("Na save notitienaam = " + huidigeNotitie.getNaam());
            System.out.println("Na save notitietekst = " + huidigeNotitie.getTekst());
            System.out.println("Na save notitiedatum = " + huidigeNotitie.getStartDatum());
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;


    }


}
//    @PostMapping("/groepDetail/{groepId}/groepWijzig")
//    public String wijzigenGroepsNaam(@ModelAttribute("groepsNaamWijzigingsFormulier")
//                                     Groep groep, @PathVariable("groepId") Integer groepId, BindingResult result) {
//        if (result.hasErrors()) {
//            return "groepDetail";
//        } else {
//            Groep huidigeGroep = groepRepository.findByGroepId(groepId);
//            System.out.println("Postmapping" + huidigeGroep.getGroepsNaam());
//            huidigeGroep.setGroepsNaam(groep.getGroepsNaam());
//            groepRepository.save(huidigeGroep);
//            System.out.println("Save" + huidigeGroep.getGroepsNaam());
//            return "redirect:/groepDetail/" + groepId;
//        }
//    }


//    @PostMapping("/wijzigen")
//    public String wijzigenHuidigeGebruiker(@ModelAttribute("gebruikersWijzigingsFormulier") Gebruiker gebruiker,
//                                    BindingResult result, Principal principal) {
//        if (result.hasErrors() || !gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
//            // TODO Als de gebruiker een niet matchend wachtwoord heeft, wordt hij nu zonder foutmelding teruggeleid naar de pagina.
//            return "redirect:/gebruikerWijzig";
//        } else {
//            Gebruiker huidigeGebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
//
//            // Als het wachtwoord, en het bevestigde wachtwoord matchen, wordt deze opgeslagen voor de gebruiker.
//            if (!gebruiker.getWachtwoord().equals("") && !gebruiker.getTrancientWachtwoord().equals("")) {
//                huidigeGebruiker.setWachtwoord(passwordEncoder.encode(gebruiker.getWachtwoord()));
//            }
//
//            huidigeGebruiker.setVoornaam(gebruiker.getVoornaam());
//            huidigeGebruiker.setAchternaam(gebruiker.getAchternaam());
//            huidigeGebruiker.setEmail(gebruiker.getEmail());
//
//            gebruikerRepository.save(huidigeGebruiker);
//            return "redirect:/gebruikerDetail";
//        }
//    }