package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Gebruiker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import MakePlannieWork.Plannie.repository.GebruikerRepository;

@Controller
public class GebruikerController {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @GetMapping({"/index" , "/"})
    String index(Model model) {
        //MakePlannieWork.Plannie.MakePlannieWork.Plannie.model.addAttribute("loginForm", new Gebruiker());
        return "index";
    }

    @GetMapping("/registreren")
    public String registreren(Model model) {
        model.addAttribute("registratieFormulier", model);
        return "gebruikerNieuw";
    }

    @PostMapping("/registreren")
    public String nieuweGebruiker(Gebruiker gebruiker, Model model) {
        model.addAttribute(gebruiker);
        if (gebruiker != null && gebruiker.getWachtwoord().equals(gebruiker.getTrancientWachtwoord())) {
            gebruikerRepository.save(gebruiker);
            return index(model);
        }
        return "registratieError";
    }
}
