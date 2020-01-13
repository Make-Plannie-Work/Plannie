package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.PollOptie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.PollOptiesRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieReisItemService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private PollOptiesRepository pollOptiesRepository;

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

    // Navigeer naar de pagina waar je een poll aan kunt maken
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/PollAanmaken")
    public String pollAanmaken(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());

            model.addAttribute("pollAanmakenFormulier", new Poll());
            return "pollNieuw";
        }

        return "reisItemDetail";
    }

    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuwePoll")
    public String pollOpslaan(@ModelAttribute("pollAanmakenFormulier") @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Poll poll, Principal principal, BindingResult result) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);

        if (reisItemOptional.isPresent()) {
            ReisItem reis = reisItemOptional.get();

            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reis);
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", reis);

            System.out.println(poll.getNaam());
            System.out.println(poll.getEindDatum());

            // We gebruiken de String eindDatum om tijdelijk de keuze van de gebruiker op te slaan.
            // Deze wordt hier uitgelezen naar poll opties, en daarna weer leeggehaald.
            String[] opties = poll.getEindDatum().split(",");
            poll.setEindDatum(null);

            reisItemRepository.save(poll);
            System.out.println("Poll opgeslagen: " + poll.getReisItemId() + ", " + poll.getNaam());

            for (String tekst : opties) {
                // Elke poll optie wordt ingevuld met een getrimde versie van de gebruikers invoer, en opgeslagen.
                PollOptie optie = new PollOptie();
                optie.setPoll(poll);
                optie.setStemOptie(tekst.trim());

                poll.voegPollOptieToe(optie);
                pollOptiesRepository.save(optie);
                System.out.println("Optie toegevoegd: " + optie.getPollOptieId() + ", " + optie.getStemOptie());
            }


        }

        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }
}
