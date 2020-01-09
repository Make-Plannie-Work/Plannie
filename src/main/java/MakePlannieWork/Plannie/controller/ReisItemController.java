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

            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reis);
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", reis);

            // ReisItem aan reis koppelen, en ReisItem aan reis toevoegen.
            notitie.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(notitie);

            reisItemRepository.save(notitie);
            reisItemRepository.save(reis);
        }

        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Gebruiker gaat naar scherm waar reisItem gewijzigd kan worden
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/notitieWijzig")
    public String huidigeNotitie(@PathVariable("notitie") Integer reisItemid, Model model, Principal principal) {
        //Optional<ReisItem> reisItemOptional = reisItemRepository.findById(reisItemid);
        //System.out.println("Titel?" + reisItemOptional.get().getNaam());
        model.addAttribute("notitie", reisItemRepository.findReisItemByReisItemId(reisItemid));
        model.addAttribute("notitieWijzigingsFormulier", new Notitie());
        return "notitieWijzig";
    }

    //    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/notitieWijzig")
    //    public String huidigeNotitie(@PathVariable("reisItemId") Integer reisItemid, Model model) {
    //        Notitie huidigeNotitie = reisItemRepository.findReisItemByReisItemId(reisItemid);
    //
    //        model.addAttribute("notitie", huidigeNotitie);
    //        model.addAttribute("notitieWijzigingsFormulier", new Notitie());


    }
