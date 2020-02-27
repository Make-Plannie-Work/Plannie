package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.dto.ActiviteitDTO;
import MakePlannieWork.Plannie.model.reisitem.*;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.PollOptiesRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import MakePlannieWork.Plannie.service.PlannieReisItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.HashSet;
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

    @Value("${plannie.mapsAPI}")
    private String mapsAPI;

    // Vanuit een Groep een Reis aanmaken
    @PostMapping("{groepId}/reisItemAanmaken")
    public String nieuwReisItem(ReisItem reisItem, @PathVariable("groepId") Integer groepId, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        if (reisItem.getNaam() != null && !reisItem.getNaam().isEmpty()) {
            plannieReisItemService.voegReisItemToe(groepOptional.get(), reisItem, principal);
            return "redirect:/" + groepId + "/reisItemDetail/" + reisItem.getReisItemId();
        }
        return "error";
    }

    // Vanuit een ReisItem de naam van het ReisItem wijzigen
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/reisNaamWijzigen")
    public String reisNaamWijzigen(@ModelAttribute("reisNaamWijzigingsFormulier")
                                           ReisItem reisItem, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, BindingResult result) {
        Optional<ReisItem> reisItemOptional = reisItemRepository.findById(reisItemId);
        if (reisItemOptional.isEmpty() || result.hasErrors()) {
            return "groepDetail";
        } else {
            ReisItem huidigReisItem = reisItemOptional.get();
            huidigReisItem.setNaam(reisItem.getNaam());
            huidigReisItem.wijzigCompleteReisDatum(reisItem.getStartDatum());
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
            // Het ReisItemId van de reis wordt opgevraagd, zodat er niet foutief een detail overzicht van een sub-reisitem wordt getoond.
            Integer hoofdReisId = reisItemOptional.get().vindHoofdReisId();
            Optional<ReisItem> hoofdReis = reisItemRepository.findById(hoofdReisId);

            if (hoofdReis.isPresent()) {
                model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
                model.addAttribute("reisItem", hoofdReis.get());
                model.addAttribute("reisNaamWijzigingsFormulier", hoofdReis.get());
                model.addAttribute("groepslidEmail", new Gebruiker());
                model.addAttribute("groep", groepOptional.get());
                model.addAttribute("mapsAPI", mapsAPI);
                return "reisItemDetail";
            }
        }
        return "redirect:/groepDetail";
    }

    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/BudgetOverzicht")
    public String budgetOverzicht(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groep", groepOptional.get());

            return "budgetOverzicht";
        }
        return "reisItemDetail";
    }

    // Ga naar pagina waar je notitie kan aanmaken
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/NotitieAanmaken")
    public String notitieAanmaken(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groep", groepOptional.get());

            model.addAttribute("notitieAanmakenFormulier", new Notitie());
            return "notitieNieuw";
        }
        return "reisItemDetail";
    }

    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/ActiviteitAanmaken")
    public String activiteitAanmaken(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("mapsAPI", mapsAPI);

            model.addAttribute("notitie", new Notitie());
            model.addAttribute("activiteitAanmakenFormulier", new ActiviteitDTO());
            return "activiteitNieuw";
        }
        return "reisItemDetail";
    }

    // Nieuwe activiteit opslaan
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuweActiviteit")
    public String activiteitOpslaan(@ModelAttribute("activiteitAanmakenFormulier") ActiviteitDTO activiteitDTO,
                                    @PathVariable("groepId") Integer groepId,
                                    @PathVariable("reisItemId") Integer reisItemId) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Activiteit activiteit = new Activiteit();
        Notitie notitie = null;
        Locatie locatie = null;
        boolean locatieEnNotitieKloppen = true;

        // Activiteit setten
        activiteit.setSoortActiviteit(activiteitDTO.getSoortActiviteit());
        activiteit.setNaam(activiteitDTO.getNaam());
        activiteit.setStartDatum(activiteitDTO.getStartDatum());
        activiteit.setBudget(activiteitDTO.getBudget());
        activiteit.setOmschrijving(activiteitDTO.getOmschrijving());

        // Notitie setten wanneer nodig en controleren op juistheid.
        if (activiteitDTO.isNotitieNodig()) {
            notitie = new Notitie();
            notitie.setNaam(activiteitDTO.getNotitieNaam());
            notitie.setTekst(activiteitDTO.getNotitieTekst());
            notitie.setStartDatum(activiteitDTO.getStartDatum());
            if (notitie.getNaam().equals("") || notitie.getTekst().length() <= 1) {
                locatieEnNotitieKloppen = false;
            }
        }
        // Locatie setten wanneer nodig en controleren op juistheid.
        if (activiteitDTO.isLocatieNodig()) {
            locatie = new Locatie();
            locatie.setNaam(activiteitDTO.getLocatieNaam());
            locatie.setAdres(activiteitDTO.getLocatieAdres());
            locatie.setStartDatum(activiteitDTO.getStartDatum());
            locatie.setLatitude(activiteitDTO.getLocatieLatitude());
            locatie.setLongitude(activiteitDTO.getLocatieLongitude());
            if (locatie.getNaam().equals("") || locatie.getAdres().equals("")) {
                locatieEnNotitieKloppen = false;
            }
        }

        if (reisItemOptional.isEmpty() || !locatieEnNotitieKloppen) {
            return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId + "/ActiviteitAanmaken";
        } else {
            ReisItem reis = reisItemOptional.get();
            reisItemId = reis.getHoofdReisItemId();

//          ReisItem aan reis koppelen, en ReisItem aan reis toevoegen.
            activiteit.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(activiteit);
            reisItemRepository.save(activiteit);
            reisItemRepository.save(reis);

            // Wanneer nodig, worden notitie en locatie toegevoegd.
            if (notitie != null) {
                notitie.setGekoppeldeReisItemId(activiteit);
                activiteit.voegReisItemToe(notitie);
                reisItemRepository.save(notitie);
                reisItemRepository.save(activiteit);

            }
            if (locatie != null) {
                locatie.setGekoppeldeReisItemId(activiteit);
                activiteit.voegReisItemToe(locatie);
                reisItemRepository.save(locatie);
                reisItemRepository.save(activiteit);
            }
        }
        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Klaarzetten van het Activiteit wijzigen Overzicht
    @GetMapping("/{groepId}/{reisItemId}/{reisItemsId}/activiteitWijzigen")
    public String huidigeActiviteit(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                    @PathVariable("reisItemsId") Integer activiteitId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Optional<ReisItem> notitieOptional = plannieReisItemService.findById(activiteitId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent() && notitieOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("reisItems", reisItemRepository.findActiviteitByReisItemId(activiteitId));
            model.addAttribute("activiteitWijzigFormulier", reisItemRepository.findActiviteitByReisItemId(activiteitId));
            model.addAttribute("subReisItemVerwijderFormulier", notitieOptional.get());
            model.addAttribute("mapsAPI", mapsAPI);
            return "activiteitWijzig";
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Opslaan van gewijzigde activiteit
    @PostMapping("/{groepId}/{reisItemId}/{reisItemsId}/activiteitWijzigen")
    public String activiteitWijzigen(@ModelAttribute("activiteitWijzigFormulier") Activiteit activiteit, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, @PathVariable("reisItemsId") Integer activiteitId, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/activiteitWijzigen";
        } else {
            Activiteit huidigeActiviteit = reisItemRepository.findActiviteitByReisItemId(activiteitId);
            huidigeActiviteit.setNaam(activiteit.getNaam());
            huidigeActiviteit.setStartDatum(activiteit.getStartDatum());
            huidigeActiviteit.setOmschrijving(activiteit.getOmschrijving());
            huidigeActiviteit.setSoortActiviteit(activiteit.getSoortActiviteit());
            huidigeActiviteit.setBudget(activiteit.getBudget());
            reisItemRepository.save(huidigeActiviteit);
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Nieuwe notitie opslaan
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuweNotitie")
    public String notitieOpslaan(@ModelAttribute("notitieAanmakenFormulier") Notitie notitie,
                                 @PathVariable("groepId") Integer groepId,
                                 @PathVariable("reisItemId") Integer reisItemId) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && !notitie.getTekst().equals("")) {
            ReisItem reis = reisItemOptional.get();
            reisItemId = reis.getHoofdReisItemId();

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
            reisItemId = reis.getHoofdReisItemId();

            // We gebruiken de String eindDatum om tijdelijk de keuze van de gebruiker op te slaan.
            // Deze wordt hier uitgelezen naar poll opties, en daarna weer leeggehaald.
            String[] opties = poll.getLocatie().split(",");
            poll.setLocatie(null);

            int optieIndex = 0;
            for (String tekst : opties) {
                // Elke poll optie wordt ingevuld met een getrimde versie van de gebruikers invoer, en daarna opgeslagen.
                tekst = tekst.trim();
                if (!tekst.equals("")) {

                    PollOptie optie = new PollOptie.Builder(tekst.trim())
                            .metIndex(optieIndex)
                            .vanPoll(poll)
                            .build();

                    optieIndex++;

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
            Poll poll = reisItemRepository.findPollByReisItemId(pollId);
            model.addAttribute("poll", poll);
            model.addAttribute("subReisItemVerwijderFormulier", poll);
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

            poll.gebruikerStemt(optieId, gebruiker);
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
            model.addAttribute("notitieWijzigingsFormulier", reisItemRepository.findNotitieByReisItemId(notitieId));
            model.addAttribute("subReisItemVerwijderFormulier", notitieOptional.get());
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
            huidigeNotitie.setBudget(notitie.getBudget());
            reisItemRepository.save(huidigeNotitie);
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    @PostMapping("{groepId}/reisItemDetail/{reisItemId}/uploadReisItemImage")
    public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId) {
        ReisItem huidigReisItem = reisItemRepository.findById(reisItemId).get();
        Groep huidigeGroep = groepRepository.findByGroepId(groepId);
        try {
            plannieReisItemService.saveImage(imageFile, huidigReisItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Ga naar pagina waar je locatie kan aanmaken
    @GetMapping("/{groepId}/reisItemDetail/{reisItemId}/LocatieAanmaken")
    public String locatieAanmaken(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("groepslidEmail", new Gebruiker());
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("mapsAPI", mapsAPI);

            model.addAttribute("locatieAanmakenFormulier", new Locatie());
            return "reisItemLocatieNieuw";
        }
        return "reisItemDetail";
    }

    // Nieuwe locatie opslaan
    @PostMapping("/{groepId}/reisItemDetail/{reisItemId}/nieuweLocatie")
    public String locatieOpslaan(@ModelAttribute("locatieAanmakenFormulier") Locatie locatie,
                                 @PathVariable("groepId") Integer groepId,
                                 @PathVariable("reisItemId") Integer reisItemId) {

        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);

        if (reisItemOptional.isPresent()) {
            ReisItem reis = reisItemOptional.get();
            reisItemId = reis.getHoofdReisItemId();

            // ReisItem aan reis koppelen, en ReisItem aan reis toevoegen.
            locatie.setGekoppeldeReisItemId(reis);
            reis.voegReisItemToe(locatie);
            reisItemRepository.save(locatie);
            reisItemRepository.save(reis);
        }

        // Terug naar reis overzicht.
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Klaarzetten van het Locatie wijzigen Overzicht
    @GetMapping("/{groepId}/{reisItemId}/{reisItemsId}/LocatieWijzigen")
    public String huidigeLocatie(@PathVariable("groepId") Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                 @PathVariable("reisItemsId") Integer locatieId, Model model, Principal principal) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        Optional<ReisItem> reisItemOptional = plannieReisItemService.findById(reisItemId);
        Optional<ReisItem> notitieOptional = plannieReisItemService.findById(locatieId);

        if (reisItemOptional.isPresent() && groepOptional.isPresent() && notitieOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("groep", groepOptional.get());
            model.addAttribute("reisItem", reisItemOptional.get());
            model.addAttribute("reisItems", reisItemRepository.findLocatieByReisItemId(locatieId));
            model.addAttribute("locatieWijzigingsFormulier", reisItemRepository.findLocatieByReisItemId(locatieId));
            model.addAttribute("subReisItemVerwijderFormulier", notitieOptional.get());
            model.addAttribute("mapsAPI", mapsAPI);
            return "reisItemLocatieWijzig";
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Opslaan van gewijzigde locatie
    @PostMapping("/{groepId}/{reisItemId}/{reisItemsId}/locatieWijzigen")
    public String locatieWijzigen(@ModelAttribute("locatieWijzigingsFormulier") Locatie locatie, @PathVariable("groepId")
            Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                  @PathVariable("reisItemsId") Integer locatieId, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/locatieWijzig";
        } else {
            Locatie huidigeLocatie = reisItemRepository.findLocatieByReisItemId(locatieId);
            huidigeLocatie.setNaam(locatie.getNaam());
            huidigeLocatie.setStartDatum(locatie.getStartDatum());
            huidigeLocatie.setAdres(locatie.getAdres());
            huidigeLocatie.setLatitude(locatie.getLatitude());
            huidigeLocatie.setLongitude(locatie.getLongitude());
            reisItemRepository.save(huidigeLocatie);
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }

    // Verwijderen van subReisItem
    @GetMapping("/{groepId}/{reisItemId}/{reisItemsId}/subReisItemVerwijderen")
    public String subReisItemVerwijderen(@PathVariable("groepId")
                                                 Integer groepId, @PathVariable("reisItemId") Integer reisItemId,
                                         @PathVariable("reisItemsId") Integer subReisItemId) {
        Optional<ReisItem> huidigeSubReisItem = reisItemRepository.findById(subReisItemId);

        if (huidigeSubReisItem.isPresent()) {
            if (huidigeSubReisItem.get() instanceof Poll) {
                // Als het te verwijderen reisItem een poll is, worden eerst de pollOpties verwijdert,
                // om foreign constraints tegen te gaan bij het verwijderen van de Poll.
                ((Poll) huidigeSubReisItem.get()).setPollOpties(new HashSet<>());
                reisItemRepository.save(huidigeSubReisItem.get());
            }

            reisItemRepository.deleteById(subReisItemId);
        }
        return "redirect:/" + groepId + "/reisItemDetail/" + reisItemId;
    }
}