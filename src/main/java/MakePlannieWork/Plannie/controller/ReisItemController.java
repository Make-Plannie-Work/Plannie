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
    public String nieuwReisItem(ReisItem reisItem, @PathVariable("groepId") Integer groepId, Principal principal) {
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
    public String notitieOpslaan(@ModelAttribute("notitieAanmakenFormulier") Notitie notitie,
                                 @PathVariable("groepId") Integer groepId,
                                 @PathVariable("reisItemId") Integer reisItemId) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && !notitie.getTekst().equals("")) {
            ReisItem reis = reisItemOptional.get();

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

    // Niewe poll opslaan
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuwePoll")
    public String pollOpslaan(@ModelAttribute("pollAanmakenFormulier") Poll poll, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent()) {
            ReisItem reis = reisItemOptional.get();

            // We gebruiken de String eindDatum om tijdelijk de keuze van de gebruiker op te slaan.
            // Deze wordt hier uitgelezen naar poll opties, en daarna weer leeggehaald.
            String[] opties = poll.getEindDatum().split(",");
            poll.setEindDatum(null);

            for (String tekst : opties) {
                // Elke poll optie wordt ingevuld met een getrimde versie van de gebruikers invoer, en daarna opgeslagen.
                tekst = tekst.trim();
                if (!tekst.equals("")) {
                    PollOptie optie = new PollOptie();
                    optie.setPoll(poll);
                    optie.setStemOptie(tekst.trim());

                    poll.voegPollOptieToe(optie);
                }
            }

            // ReisItem aan reis koppelen, en ReisItem aan reis toevoegen.
            poll.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(poll);
            reisItemRepository.save(poll);
            reisItemRepository.save(reis);
        }

        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Ga naar de PollDetails pagina
    @GetMapping("/{groepId}/{reisId}/PollDetail/{pollId}")
    public String pollDetails(@PathVariable("groepId") Integer groepId, @PathVariable("reisId") Integer reisId, @PathVariable("pollId") Integer pollId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisId);
        Optional<ReisItem> pollOptional = plannieReisItemService.findById(pollId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent() && pollOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("reis", reisItemOptional.get());
            model.addAttribute("poll", reisItemRepository.findPollByReisItemId(pollId));
            return "pollDetail";
        }

        return "reisItemDetail";
    }

    // Stemmen op een gekozen poll optie.
    @GetMapping("/{groepId}/{reisId}/PollDetail/{pollId}/StemmenOp/{pollOptieId}")
    public String stemmenOpPoll(@PathVariable("groepId") Integer groepId, @PathVariable("reisId") Integer reisId, @PathVariable("pollId") Integer pollId, @PathVariable("pollOptieId") Integer optieId, Model model, Principal principal) {

        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute("currentUser", gebruiker);
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisId);
        Poll poll = reisItemRepository.findPollByReisItemId(pollId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent() && poll != null) {
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("reis", reisItemOptional.get());

            poll.gebruikerStemt(optieId,gebruiker);
            reisItemRepository.save(poll);

            model.addAttribute("poll", poll);
        }

        return "redirect:/" + groepId + "/" + reisId + "/PollDetail/" + pollId;
    }


    // Klaarzetten van het Notitie wijzigen Overzicht
    @GetMapping("/{groepId}/{reisItemId}/{reisItemsId}/NotitieWijzigen")
    public String huidigeNotitie(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                 @PathVariable("reisItemsId") Integer notitieId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Optional<ReisItem> notitieOptional = plannieReisItemService.findById(notitieId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent() && notitieOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("reisItems", reisItemRepository.findNotitieByReisItemId(notitieId));
            model.addAttribute("notitieWijzigingsFormulier", new Notitie());
            return "notitieWijzig";
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Opslaan van gewijzigde notitie
    @PostMapping("/{groepId}/{reisItemId}/{reisItemsId}/notitieWijzigen")
    public String notitieWijzigen(@ModelAttribute("notitieWijzigingsFormulier") Notitie notitie, @PathVariable("groepId")
                                  Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                  @PathVariable("reisItemsId") Integer notitieId, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/notitieWijzig";
        } else {
            Notitie huidigeNotitie = reisItemRepository.findNotitieByReisItemId(notitieId);
            huidigeNotitie.setNaam(notitie.getNaam());
            huidigeNotitie.setTekst(notitie.getTekst());
            huidigeNotitie.setStartDatum(notitie.getStartDatum());
            reisItemRepository.save(huidigeNotitie);
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }
}