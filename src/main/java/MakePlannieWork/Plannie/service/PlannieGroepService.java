package MakePlannieWork.Plannie.service;
import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public void voegGroepToe(Groep groep, String email, Principal principal) {
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(email);
        groep.getGroepsleden().add(gebruiker);
        groep.setAanmaker(gebruikerRepository.findGebruikerByEmail(principal.getName()).getGebruikersId());
        groepRepository.save(groep);
    }
    public Optional<Groep> findById(Integer id) {
        return groepRepository.findById(id);
    }
    public void voegGebruikerToeAanGroep(Integer uitgenodigdeGebruiker, Integer groepId) {
        Groep groep = groepRepository.findById(groepId).get();
        groep.getGroepsleden().add(gebruikerRepository.findById(uitgenodigdeGebruiker).get());
        groepRepository.save(groep);
    }
    public void verwijderGebruikerUitGroep(Integer id, Integer groepId) {
        Groep groep = groepRepository.findById(groepId).get();
        groep.getGroepsleden().remove(gebruikerRepository.findById(id).get());
        groepRepository.save(groep);
    }
    public void stuurUitnodigingPerEmail(String email, Integer groepId, String gebruikerUUID, HttpServletRequest request) throws MessagingException {
        Groep groep = groepRepository.findById(groepId).get();
        String URL = request.getScheme() + "://" + request.getServerName() + "/registreren?gebruikerUUID=" + gebruikerUUID;
        mailingService.sendEmail(email, "Hallo, u bent door " + gebruikerRepository.findGebruikerByGebruikersId(groep.getAanmaker()).getVoornaam() + " " + gebruikerRepository.findGebruikerByGebruikersId(groep.getAanmaker()).getAchternaam() + " " +  " uitgenodigd voor de groep " + groep.getGroepsNaam() +
                ". Plannie maakt het groepen makkelijk om reizen te plannen. Klink op de volgende link om mee te doen: " + URL, "Uitnodiging voor Plannie");
    }
}