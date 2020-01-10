package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.service.PlannieGebruikersService;
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

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

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

    @GetMapping("/groepAanmaken")
    public String nieuweGroep(Model model) {
        model.addAttribute("nieuweGroepFormulier", new Groep());
        return "groepNieuw";
    }

    @PostMapping("/groepAanmaken")
    public String nieuweGroep (Groep groep, Model model, Principal principal) {
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
        List<Gebruiker> alleGebruikersInGroep = gebruikerRepository.findByGroepen_groepsNaam(groepRepository.findById(id).get().getGroepsNaam());
        List<Gebruiker> alleGebruikers = gebruikerRepository.findAll();
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        Set<ReisItem> alleReisItems = plannieReisItemService.getLijstMetReisItemsOpGroepId(id);
        model.addAttribute(gebruiker);
        model.addAttribute("lijstMetReisItems", alleReisItems);
        model.addAttribute("AlleLedenLijst", alleGebruikers);
        model.addAttribute("groepsLedenLijst", alleGebruikersInGroep);
        if (groepOptional.isPresent()) {
            model.addAttribute("currentUser", gebruikerRepository.findGebruikerByEmail(principal.getName()));
            model.addAttribute("groep", groepOptional.get());
            return "groepDetail";
        }
        return "redirect:/gebruikerDetail";
    }


    @GetMapping("/groepDetail/{groepId}/voegGebruikerToeAanGroep/{gebruikersId}")
    public String voegGebruikerToeAanGroep(@PathVariable("groepId") Integer groepId, @PathVariable("gebruikersId") Integer gebruikersId, Principal principal) {
        plannieGroepService.voegGebruikerToeAanGroep(gebruikersId, groepId);
        return "redirect:/groepDetail/" + groepId;
    }

    @GetMapping("/groepDetail/{groepId}/voegLedenToeAanGroepViaEmail")
    public String voegLedenToeAanGroepViaEmail(Model model, HttpServletRequest request) {
        model.addAttribute("groepslidEmail", new Gebruiker());
        return "gebruikerNieuw";
    }

    @PostMapping("/groepDetail/{groepId}/voegLedenToeAanGroepViaEmail")
    public String voegLedenToeAanGroepViaEmail(@PathVariable("groepId") Integer groepId, @ModelAttribute("groepslidEmail") Gebruiker gebruiker,  BindingResult result, Model model, HttpServletRequest request) throws MessagingException {
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
            plannieGroepService.voegGebruikerToeAanGroep(gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail()).getGebruikersId(), groepId);
            plannieGroepService.stuurUitnodigingPerEmail(gebruiker.getEmail(), groepId, gebruikerRepository.findGebruikerByEmail(gebruiker.getEmail()).getIdentifier(), request);
        } return "redirect:/groepDetail/" + groepOptional.get().getGroepId();
    }

    @GetMapping("/groepDetail/{groepId}/VerwijderLedenUitGroep/{gebruikersId}")
    public String VerwijderLedenUitGroep(@PathVariable("groepId") Integer groepId, @PathVariable("gebruikersId") Integer gebruikersId, Principal principal) {
        plannieGroepService.verwijderGebruikerUitGroep(gebruikersId, groepId);
        if (groepRepository.findByGroepId(groepId).getGroepsleden().contains(gebruikerRepository.findByEmail(principal.getName()).get())) {
            return "redirect:/groepDetail/" + groepId;
        } else {
            return "redirect:/gebruikerDetail";
        }
    }

//    // Gebruiker gaat naar scherm waar naam van groep gewijzigd kan worden
//    @GetMapping("/groepDetail/{groepId}/groepWijzig")
//    public String huidigeGroep(@PathVariable("groepId") Integer id, Model model) {
//        Optional<Groep> groepOptional = plannieGroepService.findById(id);
//        model.addAttribute("groep", groepOptional.get());
//        System.out.println("Getmapping" + groepOptional.get().getGroepsNaam());
//        model.addAttribute("groepsNaamWijzigingsFormulier", new Groep());
//        return "groepWijzig";
//    }


    @PostMapping("/groepDetail/{groepId}/groepWijzig")
    public String wijzigenGroepsNaam(@ModelAttribute("groepsNaamWijzigingsFormulier")
                                     Groep groep, @PathVariable("groepId") Integer groepId, BindingResult result) {
        if (result.hasErrors()) {
            return "groepDetail";
        } else {
            Groep huidigeGroep = groepRepository.findByGroepId(groepId);
            System.out.println("Postmapping" + huidigeGroep.getGroepsNaam());
            huidigeGroep.setGroepsNaam(groep.getGroepsNaam());
            groepRepository.save(huidigeGroep);
            System.out.println("Save" + huidigeGroep.getGroepsNaam());
            return "redirect:/groepDetail/" + groepId;
        }
    }
}
