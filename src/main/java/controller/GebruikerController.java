package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import repository.GebruikerRepository;

@Controller
public class GebruikerController {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @GetMapping("/registreren")
    public String registreren(Model model) {
        model.addAttribute("registratieFormulier", model);
        return "registreren";
    }

}
