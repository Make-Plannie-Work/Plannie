package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.service.PlannieGebruikersService;
import MakePlannieWork.Plannie.service.PlannieGroepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @GetMapping("/groepAanmaken")
    public String nieuweGroep(Model model) {
        model.addAttribute("nieuweGroepFormulier", new Groep());
        return "groepNieuw";
    }

    @PostMapping("/groepAanmaken")
    public String nieuweGroep (Groep groep, Model model, Principal principal) {
        model.addAttribute(groep);
        if (groep != null && !groep.getGroepsNaam().isEmpty()) {
            groepRepository.save(groep);
            return groepDetail(groep.getGroepId(), model, principal);
        }
        return "nieuweGroepError";
    }

    @GetMapping("/groepDetail/{groepId}")
    public String groepDetail(@PathVariable("id") Integer id, Model model, Principal principal) {
        Optional<Groep> groepOptional = plannieGroepService.findById(id);
        List<Gebruiker> alleGebruikers = gebruikerRepository.findByGroepen_groepsNaam(groepRepository.findById(id).get().getGroepsNaam());
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        model.addAttribute(gebruiker);
        model.addAttribute("groepsLedenLijst", alleGebruikers);
        if (groepOptional.isPresent()) {
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

    @PostMapping("/groepDetail/{groepId}/voegLedenToeAanGroepViaEmail")
    public String voegLedenToeAanGroepViaEmail(@ModelAttribute("groepslidEmail") Gebruiker gebruiker, @PathVariable("groepId") Integer groepId, BindingResult result, Model model) {

        Optional<Groep> groepOptional = plannieGroepService.findById(groepId);
        if (!groepOptional.isPresent()) {
            result.reject("Invalid group");
        }

        if (result.hasErrors()) {
            model.addAttribute("error", result.getAllErrors());
            return "groepDetail";
        } else {

            plannieGebruikersService.voegGebruikerToe(gebruiker, groepOptional.get());
        } return "redirect:/groepDetail";
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

}
