package MakePlannieWork.Plannie.service;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import MakePlannieWork.Plannie.repository.GebruikerRepository;
import MakePlannieWork.Plannie.repository.GroepRepository;
import MakePlannieWork.Plannie.repository.ReisItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class PlannieReisItemService {

    @Autowired
    private GebruikerRepository gebruikerRepository;

    @Autowired
    private GroepRepository groepRepository;

    @Autowired
    private PlannieGroepService plannieGroepService;

    @Autowired
    private ReisItemRepository reisItemRepository;

    public void voegReisItemToe(Groep groep, ReisItem reisItem, Principal principal) {
        Gebruiker gebruiker = gebruikerRepository.findGebruikerByEmail(principal.getName());
        groep.getReisItem().add(reisItem);
        reisItem.setAanmaker(gebruiker.getGebruikersId());
        reisItemRepository.save(reisItem);
    }

    public Optional<ReisItem> findById(Integer Id) {
        return reisItemRepository.findById(Id);
    }
}
