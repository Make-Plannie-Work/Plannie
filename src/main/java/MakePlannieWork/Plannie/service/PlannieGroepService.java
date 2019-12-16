package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class PlannieGroepService {

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private GebruikerRepository gebruikerRepository;

    public Set<Groep> getLijstMetGroepenOpGebruikersnaam(String gebruikersnaam) {
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(gebruikersnaam);

        return gebruiker.getGroepen();
    }

    public Optional<Groep> findById(Integer id) {
        return groepRepository.findById(id);
    }



}
