package MakePlannieWork.Plannie.controller;

import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroepController {

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @GetMapping("/groepAanmaken")
    public String nieuweGroep(Model model) {
        model.addAttribute("nieuweGroepFormulier", model);
        return "groepNieuw";
    }

    @PostMapping("/groepAanmaken")
    public String nieuweGroep (Groep groep, Model model) {
        model.addAttribute(groep);
        if (groep != null && !groep.getGroepsNaam().isEmpty()) {
            groepRepository.save(groep);
            return groepDetail(groep, model);
        }
        return "nieuweGroepError";
    }

    @GetMapping("/groepDetail")
    public String groepDetail(Groep groep, Model model) {
        model.addAttribute(groep);
        model.addAttribute("groepsLedenLijst", gebruikerRepository.findByGroepen_groepsNaam(groep.getGroepsNaam()));
        return "groepDetail";
    }

}
