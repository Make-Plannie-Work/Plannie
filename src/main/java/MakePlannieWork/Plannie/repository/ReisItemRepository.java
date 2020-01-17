package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.Poll;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReisItemRepository extends JpaRepository<ReisItem, Integer> {

    ReisItem findGebruikerByReisItemId (Integer Id);

    ReisItem findReisItemByAanmakerAndNaam(Integer aanmakerId, String naam);

    Notitie findNotitieByGekoppeldeReisItemAndNaam(ReisItem gekoppeldReisItem, String naam);

    Poll findPollByGekoppeldeReisItemAndNaam(ReisItem gekoppeldReisItem, String naam);

    Notitie findNotitieByReisItemId(Integer Id);

    Poll findPollByReisItemId(Integer Id);

    List<ReisItem> findReisItemByNaam (String naam);

    List<Notitie> findNotitieByNaam (String naam);

    List<Poll> findPollByNaam (String naam);
}
