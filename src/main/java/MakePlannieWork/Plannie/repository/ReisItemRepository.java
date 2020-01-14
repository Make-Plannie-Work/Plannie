package MakePlannieWork.Plannie.repository;

import MakePlannieWork.Plannie.model.Gebruiker;
import MakePlannieWork.Plannie.model.Groep;
import MakePlannieWork.Plannie.model.reisitem.Notitie;
import MakePlannieWork.Plannie.model.reisitem.ReisItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReisItemRepository extends JpaRepository<ReisItem, Integer> {

    ReisItem findGebruikerByReisItemId (Integer Id);

    Notitie findReisItemByReisItemId (Integer Id);

    List<ReisItem> findReisItemByNaam (String naam);

    List<Notitie> findNotitieByNaam (String naam);
}
