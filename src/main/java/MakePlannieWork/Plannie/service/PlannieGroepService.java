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

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private PlannieMailingService mailingService;

    public Set<Groep> getLijstMetGroepenOpGebruikersnaam(String gebruikersnaam) {
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(gebruikersnaam);

        return gebruiker.getGroepen();
    }

    public Optional<Groep> findById(Integer id) {
        return groepRepository.findById(id);
    }

    public void voegGebruikerToeAanGroep(Integer uitgenodigdeGebruiker, Integer groepId) {
        Groep groep = groepRepository.findById(groepId).get();
        groep.getGroepsleden().add(gebruikerRepository.findById(uitgenodigdeGebruiker).get());
        groepRepository.save(groep);
    }

    /*public void stuurUitnodigingPerEmail(String email, String groepUUID, HttpServletRequest request) throws MessagingException {
        Groep groep = groepRepository.findByIdentifier(groepUUID).get();

        String URL = request.getScheme() + "://" + request.getServerName() + "/register?eventUUID=" + groepUUID;
        mailingService.sendEmail(email, "Hallo, u bent door " + groep.getAanmaker().getVoornaam()+ " " + groep.getAanmaker().getAchternaam() + " uitgenodigd voor de groep " + groep.getGroepsNaam() +
                ". Plannie maakt het groepen makkelijk om reizen te plannen.");
    }*/
}
